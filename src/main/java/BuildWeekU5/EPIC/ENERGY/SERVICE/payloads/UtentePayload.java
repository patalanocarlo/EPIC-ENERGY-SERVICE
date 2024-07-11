package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;

public record UtentePayload(
        @NotEmpty(message = "Il campo Username è obbligatorio!")
        @Size(min = 3, max = 10, message = "Username deve essere compreso fra 3 e 10 caratteri")
        String userName,

        @NotEmpty(message = "Il campo Email è obbligatorio!")
        @Email(message = "Formato email non valido")
        String email,

        @NotEmpty(message = "Il campo Password è obbligatorio!")
        @Size(min = 5, max = 15, message = "La password deve essere compresa tra 5 e 15 caratteri")
        String password,

        @NotEmpty(message = "Il campo Nome è obbligatorio!")
        @Size(min = 3, max = 15, message = "Nome deve essere compreso fra 3 e 15 caratteri")
        String nome,

        @NotEmpty(message = "Il campo Cognome è obbligatorio!")
        @Size(min = 3, max = 15, message = "Cognome deve essere compreso fra 3 e 10 caratteri")
        String cognome,

        @NotEmpty(message = "Il campo Avatar è obbligatorio!")
        String avatar

) {}