package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Provincia {
    @Id
    @GeneratedValue
    UUID id;
    @CsvBindByPosition(position = 0)
    private String sigla;
    @CsvBindByPosition(position = 1)
    private String provincia;
    @CsvBindByPosition(position = 2)
    private String regione;
}
