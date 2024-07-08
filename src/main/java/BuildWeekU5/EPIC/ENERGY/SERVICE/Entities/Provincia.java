package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue
    UUID id;
    @CsvBindByName(column = "Sigla")
    private String sigla;
    @CsvBindByName(column = "Provincia")
    private String provincia;
    @CsvBindByName(column = "Regione")
    private String regione;
}
