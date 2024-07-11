package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RuoloStatoFatturaPayload(
        @NotEmpty(message = "Il campo nome è obbligatorio!")
        @Size(min = 3, max = 20, message = "Nome deve essere compreso fra 3 e 20 caratteri ")
        String nome
) {
}
