package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ComuneRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.IndirizzoRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.IndirizzoPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

@Autowired
private ComuneService comuneService;
@Autowired
private ProvincieService provincieService;

    public Indirizzo save(IndirizzoPayload body) throws IOException {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(body.via());
        indirizzo.setCivico(body.civico());
        indirizzo.setCap(body.cap());
        Provincia provinciafound = provincieService.findByName(body.provincia());
        List<Comune> comunes =  comuneService.findByNameAndProvincia(body.comune(), provinciafound);
        indirizzo.setComune(comunes.getFirst());
        return indirizzoRepository.save(indirizzo);
    }
    public Indirizzo findById(Long id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun indirizzo è stato trovato con l'id: " + id));
    }

    public void findByIdAndDelete(Long id) {
        Indirizzo found = this.findById(id);
        indirizzoRepository.delete(found);
    }
}