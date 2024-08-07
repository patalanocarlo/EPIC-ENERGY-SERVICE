package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Utente_Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Utente_Ruolo( String ruolo) {

        this.ruolo = ruolo;
    }

    private String ruolo;

}
