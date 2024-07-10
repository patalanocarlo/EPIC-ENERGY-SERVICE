package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.UnauthorizedException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.ClienteLoginPayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtenteLoginPayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClienteService clienteService;

    public String authenticateAndGenerateToken(UtenteLoginPayload payload) {
        Utente utente = this.utenteService.findByEmail(payload.email());
        if (passwordEncoder.matches(payload.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Email o password non corretta!");
        }
    }
//    public String authenticateAndGenerateTokenCliente(ClienteLoginPayload payload) {
//        Cliente cliente = clienteService.findByEmail(payload.email());
//        if (passwordEncoder.matches(payload.password(), cliente.getPassword())) {
//            return jwtTools.createToken(utente);
//        } else {
//            throw new UnauthorizedException("Email o password non corretta!");
//        }
//    }
}
