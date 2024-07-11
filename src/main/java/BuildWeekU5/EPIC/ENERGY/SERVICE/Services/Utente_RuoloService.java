package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente_Ruolo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.Utente_RuoloRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Utente_RuoloService {

    @Autowired
    private Utente_RuoloRepository utenteRuoloRepository;

    public Utente_Ruolo CreaRuoloUtente(String roleName) {
        Utente_Ruolo utenteRuolo = new Utente_Ruolo(roleName);
        return utenteRuoloRepository.save(utenteRuolo);
    }

    public void assegnaRuoloUtente(Utente utente, Long roleId) {
        Utente_Ruolo utenteRuolo = utenteRuoloRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Ruolo con id:" + roleId + " non trovato"));
        utente.setUtenteRuolo(utenteRuolo);
    }
}