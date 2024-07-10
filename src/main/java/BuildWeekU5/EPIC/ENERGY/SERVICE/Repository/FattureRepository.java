package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FattureRepository extends JpaRepository<Fatture, Long> {
    List<Fatture> findByClienteId(Long clienteId);
}