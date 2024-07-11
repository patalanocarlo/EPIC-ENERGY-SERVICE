package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClientePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClients() {
        return clienteService.getAllByOrderByNomeContatto();
    }



    @GetMapping("/{clienteId}")
    public Cliente findById(@PathVariable Long clienteId) {
        return clienteService.findById(clienteId);
    }

    @PostMapping("/me/avatar")
    public Cliente uploadAvatar(@RequestParam("avatar") MultipartFile image, @AuthenticationPrincipal Utente cliente) throws IOException {
        Cliente clienteFound = clienteService.findByEmail(cliente.getEmail());
        return this.clienteService.uploadAvatar(clienteFound.getId(), image);
    }


    @GetMapping("/orderByNome")
    public List<Cliente> getAllByOrderByNome() {
        return clienteService.getAllByOrderByNomeContatto();
    }

    @GetMapping("/orderByFatturatoAnnuale")
    public List<Cliente> getAllByOrderByFatturatoAnnuale() {
        return clienteService.getAllByOrderByFatturatoAnnuale();
    }

    @GetMapping("/orderByDataInserimento")
    public List<Cliente> getAllByOrderByDataInserimento() {
        return clienteService.getAllByOrderByDataInserimento();
    }

    @GetMapping("/orderByDataUltimoContatto")
    public List<Cliente> getAllByOrderByDataUltimoContatto() {
        return clienteService.getAllByOrderByDataUltimoContatto();
    }

    @GetMapping("/filterByFatturatoAnnuale")
    public List<Cliente> filterByFatturatoAnnuale(@RequestParam int fatturatoAnnuale) {
        return clienteService.filterByFatturatoAnnuale(fatturatoAnnuale);
    }

    @GetMapping("/filterByDataInserimento")
    public List<Cliente> filterByDataInserimento(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento) {
        return clienteService.filterByDataInserimento(dataInserimento);
    }

    @GetMapping("/filterByDataUltimoContatto")
    public List<Cliente> filterByDataUltimoContatto(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto) {
        return clienteService.filterByDataUltimoContatto(dataUltimoContatto);
    }

    @GetMapping("/filterByNomeContatto")
    public List<Cliente> filterByNomeContatto(@RequestParam String nomeContatto) {
        return clienteService.filterByNomeContatto(nomeContatto);
    }
//@GetMapping("/filterByNomeProvincia")
//public List<Cliente> filterByNomeProvincia(@RequestParam String nomeProvincia){
//
//}
    // manca solo questo come endpoint
    @PostMapping("/registration/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CLIENTE')")
    public Cliente createCliente(@RequestBody @Validated ClientePayload clientePayload, BindingResult validation, @AuthenticationPrincipal Utente cliente) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return clienteService.save(clientePayload, cliente);
    }
}
