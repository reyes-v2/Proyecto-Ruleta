package dev.rmpedro.appruleta.mapper;

import dev.rmpedro.appruleta.models.dto.ApuestaDTO;
import dev.rmpedro.appruleta.models.entities.Apuesta;

public class MapperApuesta {
    public static ApuestaDTO mapApuesta(Apuesta apuesta){
        ApuestaDTO apuestaDTO = new ApuestaDTO();
        apuestaDTO.setId(apuesta.getId());
        apuestaDTO.setValorApuesta(apuesta.getValorApuesta());
        apuestaDTO.setTipoApuesta(apuesta.getTipoApuesta());
        apuestaDTO.setMonto(apuesta.getMonto());
        apuestaDTO.setIdRuletaApuesta(apuesta.getRuleta().getId());

        return apuestaDTO;

    }
}
