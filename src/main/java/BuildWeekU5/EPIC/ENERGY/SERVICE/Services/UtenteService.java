package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente_Ruolo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.UtenteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.Utente_RuoloRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.AssegnaRuoloAdUtente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtentePayload;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utente_RuoloService utenteRuoloService;

    @Autowired
    private Utente_RuoloRepository utente_ruoloRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Utente save(UtentePayload body) throws IOException {
        utenteRepository.findByEmail(body.email()).ifPresent(

                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Utente utente = new Utente();
        utente.setUserName(body.userName());
        utente.setEmail(body.email());
        utente.setPassword(passwordEncoder.encode(body.password()));
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setAvatar("http://logoprova.it");

        Utente_Ruolo utenteRuolo= new Utente_Ruolo("USER");
        utenteRuoloService.CreaRuoloUtente(utenteRuolo);
       utente.setUtenteRuolo(utenteRuolo);
       return  utenteRepository.save(utente);
    }
    public Utente assegnaRuoloUtente(AssegnaRuoloAdUtente assegnaRuoloAdUtente) {
        Utente_Ruolo utenteRuolo = utenteRuoloService.findById(assegnaRuoloAdUtente.Ruolo());
        Utente utente = findById(assegnaRuoloAdUtente.Utente());
        utente.setUtenteRuolo(utenteRuolo);
        return utente;
    }
    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!!"));
    }

    public Page<Utente> getAllUtenti(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente uploadAvatar(Long id, MultipartFile file) throws IOException {
        Utente found = this.findById(id);
        String avatarURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(avatarURL);
        return utenteRepository.save(found);
    }
}
