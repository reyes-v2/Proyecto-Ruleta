package dev.rmpedro.appruleta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="ruletas", schema = "ruleta")
@Setter
@Getter
public class Ruleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean estaAbierta;
    private String colorGanador;
    private Integer numeroGanador;
    private Date fechaCreacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY)
    private List<Apuesta> apuestasRealizadas;

}
