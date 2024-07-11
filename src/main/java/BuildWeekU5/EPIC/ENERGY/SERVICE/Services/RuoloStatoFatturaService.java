package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.RuoloStatoFatturaidRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.RuoloStatoFatturaPayload;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RuoloStatoFatturaService {
    @Autowired
    private RuoloStatoFatturaidRepository ruoloStatoFatturaidRepository;

    public RuoloStatoFattura creaStatoFattura(RuoloStatoFatturaPayload ruoloStatoFatturaPayload){
        RuoloStatoFattura ruoloStatoFattura= new RuoloStatoFattura();
        ruoloStatoFattura.setNome(ruoloStatoFatturaPayload.nome());
        return ruoloStatoFatturaidRepository.save(ruoloStatoFattura);
    }
    public RuoloStatoFattura findStatoFatturaById(Long id){
        return ruoloStatoFatturaidRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stato della fattura non trovato"));
    }
    public Page<RuoloStatoFattura> getAllFatture(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return ruoloStatoFatturaidRepository.findAll(pageable);
    }
}
