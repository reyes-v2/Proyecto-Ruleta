package dev.rmpedro.appruleta.entities;

import dev.rmpedro.appruleta.enums.ApostarColor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="ruletas", schema = "ruleta")
@Setter
@Getter
@NoArgsConstructor
public class Ruleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean estaAbierta;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private ApostarColor colorGanador;

    private Integer numeroGanador;
    private Date fechaCreacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apuesta> apuestasRealizadas;

    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion= new Date();

    }

}
