package BuildWeekU5.EPIC.ENERGY.SERVICE.payloads;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Indirizzo;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientePayload(
        @NotEmpty(message = "Il campo Ragione Sociale è obbligatorio!")
//        @Size(min = 3, max = 10, message = "Ragione Sociale deve essere compreso fra 3 e 10 caratteri ")
        String ragioneSociale,

        @NotNull(message = "Il campo Partita Iva è obbligatorio")
//        @Max(value = 11, message = "Il campo Partita Iva deve avere massimo 11 numeri.")
        int partitaIva,



        @NotEmpty(message = "Il campo Pec è obbligatorio!")
        @Email(message = "Formato pec non valido")
        String pec,



        @NotNull(message = "Il campo Telefono è obbligatorio")
//        @Max(value = 10, message = "Il campo Telefono deve avere massimo 10 numeri.")
       String telefono,

        @NotEmpty(message = "Il campo Nome Contatto è obbligatorio!")
        @Size(min = 3, max = 10, message = "Nome Contatto deve essere compreso fra 3 e 10 caratteri ")
        String nomeContatto,

        @NotEmpty(message = "Il campo Cognome Contatto è obbligatorio!")
        @Size(min = 3, max = 10, message = "Cognome Contatto deve essere compreso fra 3 e 10 caratteri ")
        String cognomeContatto,


        @NotNull(message = "Il campo Telefono Contatto è obbligatorio")
//        @Max(value = 20, message = "Il campo Telefono Contatto deve avere massimo 20 numeri.")
        String telefonoContatto

) {
}
