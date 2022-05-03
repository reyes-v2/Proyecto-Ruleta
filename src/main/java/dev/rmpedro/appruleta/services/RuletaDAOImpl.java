package dev.rmpedro.appruleta.services;


import dev.rmpedro.appruleta.entities.Ruleta;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuletaDAOImpl implements RuletaDAO {
    @Override
    public Optional<Ruleta> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Ruleta guardar(Ruleta ruleta) {
        return null;
    }

    @Override
    public Iterable<Ruleta> buscarTodos() {
        return null;
    }
}
