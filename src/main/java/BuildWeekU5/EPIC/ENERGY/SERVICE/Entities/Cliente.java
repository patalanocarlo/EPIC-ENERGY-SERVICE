package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ragioneSociale;
    private int partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String CognomeContatto;
    private String TelefonoContatto;
    private String LogoAziendale;


    @OneToMany
    private List<Fatture> fattures;
    @OneToOne
private Indirizzo sedeLegale;
    @OneToOne
private Indirizzo sedeOperativa;
}
