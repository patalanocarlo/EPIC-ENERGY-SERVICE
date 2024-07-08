package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.FattureRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.UtenteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtentePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class FattureService {
    @Autowired
    private FattureRepository fattureRepository;

    public Fatture save(FatturePayload body) throws IOException {
        Fatture fatture = new Fatture();
      //  fatture.setNome(body.nome());

        return fattureRepository.save(fatture);
    }


    public Fatture findById(Long id) {
        return fattureRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Fatture found = this.findById(id);
        fattureRepository.delete(found);
    }
}
