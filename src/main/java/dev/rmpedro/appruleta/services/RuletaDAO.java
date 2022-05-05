package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;

public interface RuletaDAO{
    void guardar (Ruleta ruleta);
    Ruleta buscarPorId(Integer id);
    Integer crear();
    Boolean apertura(Integer id);
    void apostar(Integer id, String color, Integer numero, Double monto);
    void girar(Integer id);
    Iterable<Apuesta> cierre(Integer id);
    Iterable<Ruleta> buscarTodos();
    void eliminarPorId(Integer id);


}
