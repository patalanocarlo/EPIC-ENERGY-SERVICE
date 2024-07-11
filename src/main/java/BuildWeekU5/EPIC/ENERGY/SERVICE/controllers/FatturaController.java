package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.FattureService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.UtenteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.FatturePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FattureService fattureService;

    @GetMapping
    public Page<Fatture> getAllFatture(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return this.fattureService.getAllFatture(page, size, sortBy);
    }

    @GetMapping("/{fatturaId}")
    public Fatture findById(@PathVariable Long fatturaId) {
        return this.fattureService.findById(fatturaId);
    }

    @DeleteMapping("/{fatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable Long fatturaId) {
        this.fattureService.findByIdAndDelete(fatturaId);
    }
<<<<<<< Updated upstream

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Fatture createFattura( @RequestBody FatturePayload fatturePayload) throws IOException {
        return this.fattureService.save(fatturePayload);
    }
    @GetMapping("/cliente/{clienteId}")
    public List<Fatture> getFattureByClienteId(@PathVariable Long clienteId) {
        return fattureService.findByClienteId(clienteId);
    }
    @GetMapping("/stato/{statoId}")
    public List<Fatture> getFattureByStatoFatturaId(@PathVariable Long statoId) {
        return fattureService.findByStatoFatturaId(statoId);
    }
    @GetMapping("/data")
    public List<Fatture> getFattureByDataFattura(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFattura) {
        return fattureService.findByDataFattura(dataFattura);
    }

    @GetMapping("/anno")
    public List<Fatture> getFattureByAnno(@RequestParam int anno) {
        return fattureService.findByAnno(anno);
    }

    @GetMapping("/importo")
    public List<Fatture> getFattureByImporto(@RequestParam double importo) {
        return fattureService.getFattureByImporto(importo);
    }
}
=======
}
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('CLIENTE')")
//    public Fatture createFattura( @RequestBody FatturePayload fatturePayload, @AuthenticationPrincipal Utente cliente) throws IOException {
//        return this.fattureService.save(fatturePayload);
//    }
//}
>>>>>>> Stashed changes
