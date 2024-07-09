package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;


   @ManyToOne
    private Utente_Ruolo utenteRuolo;
}
