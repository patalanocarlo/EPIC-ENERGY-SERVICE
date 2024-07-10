package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record IndirizzoPayload(
        @NotEmpty(message = "Il campo Via è obbligatorio!")
        @Size(min = 3, max = 10, message = "Via deve essere compreso fra 3 e 10 caratteri")
        String via,

        @Positive(message = "Il campo Civico deve essere un numero positivo!")
        int civico,

        @Positive(message = "Il campo CAP deve essere un numero positivo!")
        int cap,

        @NotEmpty(message = "Il campo Provincia non può essere vuoto!")
        String provincia,
        @NotEmpty(message = "Il campo Comune non può essere vuoto!")
        String comune

) {}