package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;

public interface ApuestaDAO{


    Apuesta guardar(Apuesta entidad);

    Iterable<Apuesta> buscarApuestasPorRuletaId(Integer id);

    Apuesta crearApuesta(String valorApuesta, Double monto, Ruleta ruleta);

    Iterable<Apuesta> calcularResultados(Ruleta ruleta);


}
