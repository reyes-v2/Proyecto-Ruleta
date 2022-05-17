package dev.rmpedro.appruleta.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.rmpedro.appruleta.enums.Color;
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
    private Boolean estaAbierta=false;
    @Column(name = "color_ganador")
    @Enumerated(EnumType.STRING)
    private Color colorGanador;
    private Integer numeroGanador;
    private Date fechaCreacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ruleta"})
    private List<Apuesta> apuestasRealizadas;

    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion= new Date();

    }


}
