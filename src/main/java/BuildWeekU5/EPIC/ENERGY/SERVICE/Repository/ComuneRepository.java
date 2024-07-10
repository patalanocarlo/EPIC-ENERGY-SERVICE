package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {
    Optional<Comune> findByProvincia (String provincia);

}
