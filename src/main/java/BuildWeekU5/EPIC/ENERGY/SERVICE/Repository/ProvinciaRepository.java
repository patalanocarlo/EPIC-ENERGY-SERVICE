package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

    Optional<Provincia> findBySigla(String sigla);

    List<Provincia> findByName (String provincia);
}
