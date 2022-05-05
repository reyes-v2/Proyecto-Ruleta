package dev.rmpedro.appruleta.repositories;

import dev.rmpedro.appruleta.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository  extends CrudRepository<Ruleta, Integer> {
    @Query("select r.id, r.estaAbierta from Ruleta r")
    Iterable<Ruleta> buscar();


}
