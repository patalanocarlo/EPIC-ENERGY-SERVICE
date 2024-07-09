package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FatturePayload(
        @NotNull(message = "Campo obbligatorio")
         LocalDate DataFattura,
       @NotNull(message = "Campo obbligatario")
       double Importo,
        @NotEmpty(message = "Campo obbligatorio")
        String statoFattura
) {
}
