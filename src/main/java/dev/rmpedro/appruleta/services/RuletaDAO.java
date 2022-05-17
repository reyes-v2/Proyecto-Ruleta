package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;

public interface RuletaDAO{
    void guardar (Ruleta ruleta);
    Ruleta buscarPorId(Integer id);
    Integer crear();
    Boolean apertura(Integer id);
    Apuesta apostar(Integer id, String valorApuesta, Double monto);
    void girar(Integer id);
    Iterable<Apuesta> cierre(Integer id);
    Iterable<Ruleta> buscarTodos();


}
