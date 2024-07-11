package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.NotNull;

public record FattureResercePayload(
        @NotNull(message = "Campo obbligatorio")
        Long idFattura
) {
}
