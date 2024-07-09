package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ComuneRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.IndirizzoRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.IndirizzoPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzo save(IndirizzoPayload body) throws IOException {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(body.via());
        indirizzo.setCivico(body.civico());
        indirizzo.setCap(body.cap());


        UUID comuneId = UUID.fromString(body.comuneId());
        Comune comune = comuneRepository.findById(comuneId)
                .orElseThrow(() -> new IOException("id del comune non trovato " + body.comuneId()));
        indirizzo.setComune(comune);

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