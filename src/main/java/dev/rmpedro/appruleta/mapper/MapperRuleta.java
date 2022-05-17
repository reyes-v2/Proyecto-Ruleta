package dev.rmpedro.appruleta.mapper;

import dev.rmpedro.appruleta.models.dto.RuletaDTO;
import dev.rmpedro.appruleta.models.entities.Ruleta;

public class MapperRuleta {

    public static RuletaDTO mapRuleta(Ruleta ruleta){
        RuletaDTO ruletaDTO = new RuletaDTO();
        ruletaDTO.setId(ruleta.getId());
        ruletaDTO.setEstado(ruleta.getEstaAbierta());

        return ruletaDTO;
    }

}
