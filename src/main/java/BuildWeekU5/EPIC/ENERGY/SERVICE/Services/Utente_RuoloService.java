package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente_Ruolo;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.Utente_RuoloRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.AssegnaRuoloAdUtente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtenteRuolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Utente_RuoloService {

    @Autowired
    private Utente_RuoloRepository utenteRuoloRepository;
//    @Autowired
//    private UtenteService utenteService;

    public Utente_Ruolo CreaRuoloUtente(Utente_Ruolo utenteRuolo) {

        return utenteRuoloRepository.save(utenteRuolo);
    }
public Utente_Ruolo createRuoloUtenteDaEnpoint(UtenteRuolo utenteRuolo){
        Utente_Ruolo utente_ruolo = new Utente_Ruolo();
        utente_ruolo.setRuolo(utenteRuolo.ruolo());
        return utenteRuoloRepository.save(utente_ruolo);
}

    public Utente_Ruolo findById (Long id){
       return  utenteRuoloRepository.findById(id).orElseThrow(()-> new NotFoundException("Ruolo Utente non trovato"));
    }
}