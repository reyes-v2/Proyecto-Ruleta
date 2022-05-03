package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Ruleta;

import java.util.Optional;

public interface RuletaDAO {
    Optional<Ruleta> buscarPorId(Integer id);
    Ruleta guardar(Ruleta ruleta);
    Iterable<Ruleta> buscarTodos();


}
