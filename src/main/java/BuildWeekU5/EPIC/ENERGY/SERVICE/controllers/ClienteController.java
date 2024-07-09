package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClientePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
@Autowired
    private ClienteService clienteService;

@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody ClientePayload clientePayload) throws IOException {
    return clienteService.save(clientePayload);
}
@GetMapping("/clienteId")
    public Cliente findyById(@PathVariable Long clienteid){
    return clienteService.findById(clienteid);
}

    @PostMapping("/{clienteId}/avatar")
    public Cliente uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable Long clienteId) throws IOException {
        return this.clienteService.uploadAvatar(clienteId, image);
    }

}
