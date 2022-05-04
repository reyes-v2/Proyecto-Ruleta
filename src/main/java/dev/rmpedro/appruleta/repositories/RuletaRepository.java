package dev.rmpedro.appruleta.repositories;

import dev.rmpedro.appruleta.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository  extends CrudRepository<Ruleta, Integer> {
    @Query(value = "select ruleta.ruletas.id,ruleta.ruletas.esta_Abierta from ruleta.ruletas", nativeQuery = true)
    public Iterable<Ruleta> buscarTodos();

}
