package dev.rmpedro.appruleta.models.dto;

import dev.rmpedro.appruleta.enums.TipoApuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApuestaDTO {

    private Integer id;
    private String valorApuesta;
    private TipoApuesta tipoApuesta;
    private Double monto;
    private Integer idRuletaApuesta;
}
