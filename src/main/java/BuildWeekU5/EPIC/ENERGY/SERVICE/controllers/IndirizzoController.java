package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.IndirizzoService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.IndirizzoPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    IndirizzoService indirizzoService;
    @PostMapping("/sede-legale")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public Cliente createIndirizzoSedeLegale( @RequestBody IndirizzoPayload body,
                                             @AuthenticationPrincipal Utente cliente) throws IOException {

        Cliente clienteFound = clienteService.findByEmail(cliente.getEmail());
        Indirizzo indirizzo = indirizzoService.save(body);
        return clienteService.uploadIndirizzoSedeLegale(indirizzo, clienteFound);
    }

   @PostMapping("/sede-operativa")
   @PreAuthorize("hasAuthority('CLIENTE')")
    public Cliente createIndirizzoSedeOperativa(@RequestBody IndirizzoPayload body,
                                               @AuthenticationPrincipal Utente cliente) throws IOException {
       Cliente clienteFound = clienteService.findByEmail(cliente.getEmail());
       Indirizzo indirizzo = indirizzoService.save(body);
       return clienteService.uploadIndirizzoSedeOperativa(indirizzo, clienteFound);
    }
}
