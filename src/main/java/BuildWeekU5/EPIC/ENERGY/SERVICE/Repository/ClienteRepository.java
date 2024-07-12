package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByOrderByNomeContattoAsc();
    List<Cliente> findAllByOrderByFatturatoAnnualeDesc();
    List<Cliente> findAllByOrderByDataInserimentoDesc();
    List<Cliente> findAllByOrderByDataUltimoContattoDesc();
    List<Cliente> findByFatturatoAnnualeGreaterThanEqual(int fatturatoAnnuale);
    List<Cliente> findByDataInserimentoAfter(LocalDate dataInserimento);
    List<Cliente> findByDataUltimoContattoAfter(LocalDate dataUltimoContatto);
    List<Cliente> findByNomeContattoContainingIgnoreCase(String nomeContatto);
    Optional<Cliente> findByEmail(String email);
List<Cliente> findBySedeLegale_Comune_Provincia(String provincia);
}

