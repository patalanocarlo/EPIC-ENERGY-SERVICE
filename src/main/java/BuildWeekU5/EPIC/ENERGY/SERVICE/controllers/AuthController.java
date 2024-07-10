package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.AuthService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ClienteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.UtenteService;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.BadRequestException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public UtenteResponseAuthPayload login(@RequestBody UtenteLoginPayload payload) {
        return new UtenteResponseAuthPayload(authService.authenticateAndGenerateToken(payload));
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponsePayload register(@RequestBody @Validated UtentePayload body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new UtenteResponsePayload((long) this.utenteService.save(body).getId());
    }
    @PostMapping("/registration/client")
    @ResponseStatus(HttpStatus.CREATED)

    public Cliente createCliente(@RequestBody @Validated ClientePayload clientePayload, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return clienteService.save(clientePayload);
    }
//    @PostMapping("/login/client")
//    @ResponseStatus(HttpStatus.CREATED)
//
//    public Cliente loginCliente(@RequestBody @Validated ClienteLoginPayload clientePayload, BindingResult validation) throws IOException {
//        if (validation.hasErrors()) {
//            throw new BadRequestException(validation.getAllErrors());
//        }
//        return  new ClienteResponseAuthPayload(authService.authenticateAndGenerateToken(clientePayload));
//    }
}
