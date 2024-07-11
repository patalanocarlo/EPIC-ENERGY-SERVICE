package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
}
