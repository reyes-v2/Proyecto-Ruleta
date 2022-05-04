package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;

public interface RuletaDAO{
    void guardar (Ruleta ruleta);
    Ruleta buscarPorId(Integer id);
    Integer crear();
    Boolean estado(Integer id);
    void apostar(Integer id, Color color, Integer numero, Double monto);
   // void asignarApuestas(Integer id);
    void girar(Integer id);
    Iterable<Apuesta> cierre(Integer id);
    Iterable<Ruleta> buscarTodos();
    void eliminarPorId(Integer id);


}
