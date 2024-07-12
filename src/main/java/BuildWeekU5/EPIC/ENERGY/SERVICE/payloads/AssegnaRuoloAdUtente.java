package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AssegnaRuoloAdUtente(
        @NotNull(message = "Il campo ruolo è obbligatorio")
        Long Ruolo,
        @NotNull(message = "Il campo id è obbligatorio")
        Long Utente
) {
}
