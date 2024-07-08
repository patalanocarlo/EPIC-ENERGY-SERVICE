package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
public class Comune {
    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    @CsvBindByPosition(position = 0)
    private String codiceProvincia;
    @NotNull
    @CsvBindByPosition(position = 1)
    private String codiceComune;
    @NotNull
    @CsvBindByPosition(position = 2)
    private String Denominazione;
    @NotNull
    @CsvBindByPosition(position = 3)
    private String provincia;

    public Comune(String codiceProvincia, String codiceComune, String denominazione, String provincia) {
       this.codiceProvincia = codiceProvincia;
        this.codiceComune = codiceComune;
        Denominazione = denominazione;
        this.provincia = provincia;
    }
}
