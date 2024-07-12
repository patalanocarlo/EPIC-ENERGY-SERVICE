package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente_Ruolo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.UtenteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.Utente_RuoloService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.AssegnaRuoloAdUtente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.RuoloStatoFatturaPayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtenteRuolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Utente_RuoloService utenteRuoloService;
    @Autowired
    UtenteService utenteService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente_Ruolo createUtenteRuolo(@RequestBody @Validated UtenteRuolo utenteRuoloPayload, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return utenteRuoloService.createRuoloUtenteDaEnpoint(utenteRuoloPayload);
    }

    @PostMapping("/assegnaRuolo")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente assegnaRuolo(@RequestBody @Validated AssegnaRuoloAdUtente utenteRuoloPayload, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return utenteService.assegnaRuoloUtente(utenteRuoloPayload);
    }
    // da testare
}
