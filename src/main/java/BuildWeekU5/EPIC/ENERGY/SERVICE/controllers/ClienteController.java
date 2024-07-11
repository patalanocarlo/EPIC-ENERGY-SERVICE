package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClientePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
@Autowired
    private ClienteService clienteService;


@GetMapping("/{clienteId}")
    public Cliente findyById(@PathVariable Long clienteId){
    return clienteService.findById(clienteId);
}

    @PostMapping("/{clienteId}/avatar")
    public Cliente uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable Long clienteId) throws IOException {
        return this.clienteService.uploadAvatar(clienteId, image);
    }
    @GetMapping("/orderByNome")
    public List<Cliente> getAllByOrderByNome() {
        return clienteService.getAllByOrderByNomeContatto();
    }
    @GetMapping("/orderByFatturatoAnnuale")
    public List<Cliente> getAllByOrderByFatturatoAnnuale() {
        return clienteService.getAllByOrderByFatturatoAnnuale();
    }

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
