package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UtenteRuolo(
        @NotEmpty (message = "Il campo è obbligatorio")
        String ruolo
) {
}
