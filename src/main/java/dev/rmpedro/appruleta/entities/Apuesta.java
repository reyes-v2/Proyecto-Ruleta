package dev.rmpedro.appruleta.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.rmpedro.appruleta.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="apuestas",schema="ruleta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Apuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;
    private Double monto;
    private Boolean esGanadora=false;
    private Double premio =0d;
    private Date fechaCreacion;

    @ManyToOne
    @JsonIgnoreProperties({"apuestasRealizadas"})
    @JoinColumn(name = "ruleta_id",foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;


    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion= new Date();

    }



}
