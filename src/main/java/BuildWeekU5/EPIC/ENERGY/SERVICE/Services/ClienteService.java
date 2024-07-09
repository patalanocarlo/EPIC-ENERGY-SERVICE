package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ClienteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.FattureRepository;
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

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Cliente save(ClientePayload body) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setEmail(body.email());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
       cliente.setNomeContatto(body.nomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setDataInserimento(LocalDate.now());
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
}
