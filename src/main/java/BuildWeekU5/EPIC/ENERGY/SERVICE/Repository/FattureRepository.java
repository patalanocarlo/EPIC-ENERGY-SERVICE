package BuildWeekU5.EPIC.ENERGY.SERVICE.Repository;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Cliente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FattureRepository extends JpaRepository<Fatture, Long> {
    List<Fatture> findByClienteId(Long clienteId);
    List<Fatture> findByRuoloStatoFatturaId(Long statoId);
    @Query("SELECT f FROM Fatture f WHERE f.DataFattura = :dataFattura")
    List<Fatture> findByDataFattura(LocalDate dataFattura);

    @Query("SELECT f FROM Fatture f WHERE YEAR(f.DataFattura) = :anno")
    List<Fatture> findByAnno(int anno);

    @Query("SELECT f FROM Fatture f WHERE f.Importo = :importo")
    List<Fatture> findByImporto(@Param("importo") double Importo);
}