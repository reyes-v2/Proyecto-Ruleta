package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;

import java.util.Optional;

public interface ApuestaDAO{


    Optional<Apuesta> buscarPorId(Integer id);
    Apuesta guardar(Apuesta entidad);
    Iterable<Apuesta> buscarApuestas(Integer id);
    void eliminarPorId(Integer id);

}
