package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente_Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Utente_RuoloRepository extends JpaRepository<Utente_Ruolo, Long> {
}
