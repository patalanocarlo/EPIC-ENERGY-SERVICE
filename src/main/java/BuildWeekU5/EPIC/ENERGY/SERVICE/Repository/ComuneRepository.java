package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.util.UUID;
@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {
    List<Comune> findByProvincia(String provincia);
    List<Comune> findByNameAndProvincia(String name, Provincia provincia);
}

