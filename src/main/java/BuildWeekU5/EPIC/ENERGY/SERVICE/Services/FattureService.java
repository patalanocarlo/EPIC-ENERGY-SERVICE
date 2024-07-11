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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class FattureService {
    @Autowired
    private FattureRepository fattureRepository;
    @Autowired
    private RuoloStatoRepository ruoloStatoFatturaRepository;
    @Autowired
    private ClienteService clienteService;
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
        Fatture savedFatture = fattureRepository.save(fatture);
        updateFatturatoAnnuale(cliente.getId());

        return savedFatture;

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

    private void updateFatturatoAnnuale(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con id: " + clienteId));
        double fatturatoAnnuale = fattureRepository.findByClienteId(clienteId)
                .stream()
                .mapToDouble(Fatture::getImporto)
                .sum();
        cliente.setFatturatoAnnuale((int) fatturatoAnnuale);
        clienteRepository.save(cliente);
    }

    public List<Fatture> findByClienteId(Long clienteId) {
        return fattureRepository.findByClienteId(clienteId);
    }

    public List<Fatture> findByStatoFatturaId(Long statoId) {
        return fattureRepository.findByRuoloStatoFatturaId(statoId);
    }
    }




