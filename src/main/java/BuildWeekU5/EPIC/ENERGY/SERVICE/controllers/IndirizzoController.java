package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.IndirizzoService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.IndirizzoPayload;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Cliente createIndirizzoSedeLegale(
                                             @AuthenticationPrincipal Cliente cliente) throws IOException {

      //  Cliente clienteFound = clienteService.findById(cliente.getId());
      //  Indirizzo indirizzo = indirizzoService.save(body);
        return cliente;
    }}

//    @PostMapping("/sede-operativa")
//    public Cliente createIndirizzoSedeOperativa(@RequestBody IndirizzoPayload body,
//                                                @AuthenticationPrincipal Cliente cliente) throws IOException {
//        Cliente clienteFound = clienteService.findById(cliente.getId());
//        Indirizzo indirizzo = indirizzoService.save(body);
//        return clienteService.uploadIndirizzoSedeOperativa(indirizzo, cliente);
//    }
//}
