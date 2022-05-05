package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;

import java.util.Optional;

public interface ApuestaDAO{


    Optional<Apuesta> buscarPorId(Integer id);
    Apuesta guardar(Apuesta entidad);
    Iterable<Apuesta> buscarApuestas(Integer id);
    void eliminarPorId(Integer id);
    Iterable<Apuesta> calcularResultados(Ruleta ruleta);
    void crearApuesta(String color, Double monto,Ruleta ruleta);
    void crearApuesta(Integer numero, Double monto,Ruleta ruleta);


}
