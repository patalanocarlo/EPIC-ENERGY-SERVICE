package BuildWeekU5.EPIC.ENERGY.SERVICE.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Fatture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate DataFattura;
    private double Importo;
    private int NumeroFattura = 0;
    @ManyToOne
    private RuoloStatoFattura ruoloStatoFattura;
    @ManyToOne
    private Cliente cliente;

    public Fatture(Cliente cliente, double importo, RuoloStatoFattura ruoloStatoFattura, LocalDate dataFattura, int numeroFattura) {
        this.cliente = cliente;
        this.Importo = importo;
        this.ruoloStatoFattura = ruoloStatoFattura;
        this.DataFattura = dataFattura;
        this.NumeroFattura = numeroFattura;
    }
}
