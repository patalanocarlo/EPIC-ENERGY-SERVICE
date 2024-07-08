package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Ruolo;
    @ManyToOne
    private Utente_Ruolo utenteRuolo;
}
