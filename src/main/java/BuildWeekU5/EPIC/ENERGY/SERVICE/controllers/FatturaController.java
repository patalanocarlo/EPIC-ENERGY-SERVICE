package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.FattureService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FattureService fattureService;


    @GetMapping
    public Page<Fatture> getAllFatture(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
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
}