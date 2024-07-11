package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.RuoloStatoFatturaService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.RuoloStatoFatturaPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ruoliStatoFattura")
public class RuoloStatoFatturaController {

    @Autowired
    private RuoloStatoFatturaService ruoloStatoFatturaService;

    @GetMapping
    public Page<RuoloStatoFattura> getAllRuoliFattura(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.ruoloStatoFatturaService.getAllFatture(page, size, sortBy);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RuoloStatoFattura createRuoloStatoFattura(@RequestBody @Validated RuoloStatoFatturaPayload ruoloStatoFatturaPayload, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.ruoloStatoFatturaService.creaStatoFattura(ruoloStatoFatturaPayload);
    }
}