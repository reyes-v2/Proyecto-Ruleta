package dev.rmpedro.appruleta.entities;

import dev.rmpedro.appruleta.enums.ApostarColor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="apuestas",schema="ruleta")
@Setter
@Getter
public class Apuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private ApostarColor color;
    private Double monto;
    private Boolean esGanadora;
    private Double premio;
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "ruleta_id",foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;


}
