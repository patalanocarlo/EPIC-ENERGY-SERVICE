package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record FatturePayload(
        @NotNull(message = "Campo obbligatorio")
        LocalDate DataFattura,

        @NotNull(message = "Campo obbligatorio")
        @Min(value = 0, message = "L'Importo deve essere maggiore di zero per essere valido")
        double Importo,

        @NotNull(message = "Campo obbligatorio")
        Long  idFattura

) {}
