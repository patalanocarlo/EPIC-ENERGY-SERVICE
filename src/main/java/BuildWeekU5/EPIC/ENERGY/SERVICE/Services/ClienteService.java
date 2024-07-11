package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.*;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ClienteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClientePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.IndirizzoPayload;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;
  @Autowired
    private ComuneService comuneService;
    @Autowired
    private ProvincieService provincieService;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public Cliente save(ClientePayload body, Utente utente) throws IOException {

        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(utente.getEmail());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setEmailContatto(utente.getEmail());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(LocalDate.now());

        cliente.setFatturatoAnnuale(0);
        cliente.setLogoAziendale("http://logoprova.it");

        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Cliente found = this.findById(id);
        clienteRepository.delete(found);
    }

    public Cliente uploadAvatar(Long id, MultipartFile file) throws IOException {
        Cliente found = this.findById(id);
        String avatarURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setLogoAziendale(avatarURL);
        return clienteRepository.save(found);
    }
    public List<Cliente> getAllByOrderByNomeContatto() {
        return clienteRepository.findAllByOrderByNomeContattoAsc();
    }
    public List<Cliente> getAllByOrderByFatturatoAnnuale() {
        return clienteRepository.findAllByOrderByFatturatoAnnualeDesc();
    }
    public List<Cliente> getAllByOrderByDataInserimento() {
        return clienteRepository.findAllByOrderByDataInserimentoDesc();
    }

    public List<Cliente> getAllByOrderByDataUltimoContatto() {
        return clienteRepository.findAllByOrderByDataUltimoContattoDesc();
    }
    public List<Cliente> filterByFatturatoAnnuale(int fatturatoAnnuale) {
        return clienteRepository.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnuale);
    }

    public List<Cliente> filterByDataInserimento(LocalDate dataInserimento) {
        return clienteRepository.findByDataInserimentoAfter(dataInserimento);
    }

    public List<Cliente> filterByDataUltimoContatto(LocalDate dataUltimoContatto) {
        return clienteRepository.findByDataUltimoContattoAfter(dataUltimoContatto);
    }

    public List<Cliente> filterByNomeContatto(String nomeContatto) {
        return clienteRepository.findByNomeContattoContainingIgnoreCase(nomeContatto);
    }

    public Cliente uploadIndirizzoSedeLegale(Indirizzo indirizzo, Cliente cliente) {
        Cliente found = this.findById(cliente.getId());
        found.setSedeLegale(indirizzo);
        return clienteRepository.save(found);
    }

    public Cliente uploadIndirizzoSedeOperativa(Indirizzo indirizzo, Cliente cliente) {
        Cliente found = this.findById(cliente.getId());
        found.setSedeOperativa(indirizzo);
        return clienteRepository.save(found);

    }
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con email " + email + " non trovato!!"));
    }

}




