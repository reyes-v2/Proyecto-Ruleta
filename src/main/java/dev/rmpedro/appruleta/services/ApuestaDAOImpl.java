package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.repositories.ApuestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApuestaDAOImpl implements ApuestaDAO{

        protected final ApuestaRepository repository;

        public ApuestaDAOImpl(ApuestaRepository repository) {
                this.repository = repository;
        }


        @Override
        public Optional<Apuesta> buscarPorId(Integer id) {
                return Optional.empty();
        }

        @Override
        public Apuesta guardar(Apuesta entidad) {
                return repository.save(entidad);
        }

        @Override
        public Iterable<Apuesta> buscarApuestas(Integer id) {
                return repository.buscar(id);
        }

        @Override
        public void eliminarPorId(Integer id) {

        }
}
