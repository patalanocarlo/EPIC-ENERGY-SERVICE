package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuoloStatoRepository extends JpaRepository<RuoloStatoFattura, Long> {
}
