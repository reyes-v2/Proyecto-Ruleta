package dev.rmpedro.appruleta.repositories;

import dev.rmpedro.appruleta.entities.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApuestaRepository  extends CrudRepository<Apuesta, Integer> {
    @Query(value = "select * from ruleta.apuestas a where a.ruleta_id=?1",nativeQuery = true)
    Iterable<Apuesta> buscar(Integer idRuleta);

}
