package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.RuoloStatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuoloStatoFatturaidRepository extends JpaRepository<RuoloStatoFattura, Long> {


}
