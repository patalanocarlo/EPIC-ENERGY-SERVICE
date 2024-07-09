package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ClienteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.FattureRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.RuoloStatoRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.UtenteRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtentePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class FattureService {
    @Autowired
    private FattureRepository fattureRepository;
    @Autowired
    private RuoloStatoRepository ruoloStatoFatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Fatture save(FatturePayload body) throws IOException {
        Fatture fatture = new Fatture();
        fatture.setDataFattura(body.DataFattura());
        fatture.setImporto(body.Importo());
        RuoloStatoFattura ruoloStatoFattura = ruoloStatoFatturaRepository.findById(body.ruoloStatoFatturaId())
                .orElseThrow(() -> new IOException("Stato fattura non trovata con id: " + body.ruoloStatoFatturaId()));
        fatture.setRuoloStatoFattura(ruoloStatoFattura);


        Cliente cliente = clienteRepository.findById(body.clienteId())
                .orElseThrow(() -> new IOException("Cliente non trovato  Con id : " + body.clienteId()));
        fatture.setCliente(cliente);

        return fattureRepository.save(fatture);
    }
    public Fatture findById(Long id) {
        return fattureRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Fatture found = this.findById(id);
        fattureRepository.delete(found);
    }
    public Page<Fatture> getAllFatture(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return fattureRepository.findAll(pageable);
    }
    }




