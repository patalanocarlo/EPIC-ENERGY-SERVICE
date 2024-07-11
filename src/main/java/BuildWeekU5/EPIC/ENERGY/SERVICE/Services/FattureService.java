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
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class FattureService {
    @Autowired
    private FattureRepository fattureRepository;
   @Autowired
   private RuoloStatoFatturaService ruoloStatoFatturaService;
    @Autowired
    private ClienteService clienteService;
@Autowired
private ClienteRepository clienteRepository;
    @Autowired
    private UtenteService utenteService;

    public Fatture save(FatturePayload body, Cliente cliente) throws IOException {
        Cliente found = clienteService.findById(cliente.getId());
        if (found == null) {
            throw new NotFoundException("Cliente non trovato con id: " + cliente.getId());
        }

        Fatture fatture = new Fatture();
        fatture.setDataFattura(body.DataFattura());
        fatture.setImporto(body.Importo());


        RuoloStatoFattura ruoloStatoFattura = ruoloStatoFatturaService.findStatoFatturaById(body.idFattura());
        if (ruoloStatoFattura == null) {
            throw new NotFoundException("RuoloStatoFattura non trovato con id: " + body.idFattura());
        }
        fatture.setRuoloStatoFattura(ruoloStatoFattura);

        fatture.setCliente(found);
        List<Fatture> modificaFatture = found.getFattures();
        if (modificaFatture == null) {
            modificaFatture = new ArrayList<>();
        }
        modificaFatture.add(fatture);
        found.setFattures(modificaFatture);

        double fatturatoAnnuale = modificaFatture.stream()
                .mapToDouble(Fatture::getImporto)
                .sum();
        found.setFatturatoAnnuale((int) fatturatoAnnuale);

        clienteRepository.save(found);
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

//    private void updateFatturatoAnnuale(Long clienteId) {
//        Cliente cliente = clienteRepository.findById(clienteId)
//                .orElseThrow(() -> new NotFoundException("Cliente non trovato con id: " + clienteId));
//        double fatturatoAnnuale = fattureRepository.findByClienteId(clienteId)
//                .stream()
//                .mapToDouble(Fatture::getImporto)
//                .sum();
//        cliente.setFatturatoAnnuale((int) fatturatoAnnuale);
//        clienteRepository.save(cliente);
//    }

    public List<Fatture> findByClienteId(Long clienteId) {
        return fattureRepository.findByClienteId(clienteId);
    }

    public List<Fatture> findByStatoFatturaId(Long statoId) {
        return fattureRepository.findByRuoloStatoFatturaId(statoId);
    }
    public List<Fatture> findByDataFattura(LocalDate dataFattura) {
        return fattureRepository.findByDataFattura(dataFattura);
    }

    public List<Fatture> findByAnno(int anno) {
        return fattureRepository.findByAnno(anno);
    }


    public List<Fatture> getFattureByImporto( double Importo) {
        return fattureRepository.findByImporto(Importo);
    }
    }




