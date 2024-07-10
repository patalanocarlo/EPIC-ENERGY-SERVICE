package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.*;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ClienteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ComuneRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.FattureRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ProvinciaRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClientePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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


    public Cliente save(ClientePayload body) throws IOException {

        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(LocalDate.now());
        cliente.setFatturatoAnnuale(0);
        cliente.setLogoAziendale("http://logoprova.it");
        Indirizzo indirizzoSedeLegale = new Indirizzo();
       indirizzoSedeLegale.setCap(body.capSedeLegale());
       indirizzoSedeLegale.setVia(body.viaSedeLegale());
       indirizzoSedeLegale.setCivico(body.numeroCivicoSedeLegale());
       Provincia provinciafound = provincieService.findByName(body.provinciaSedeLegale());
      List<Comune> comunes =  comuneService.findByNameAndProvincia(body.comuneSedeLegale(), provinciafound);
       indirizzoSedeLegale.setComune(comunes.getFirst());
       indirizzoService.save(indirizzoSedeLegale);
       cliente.setSedeLegale(indirizzoSedeLegale);
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
}
