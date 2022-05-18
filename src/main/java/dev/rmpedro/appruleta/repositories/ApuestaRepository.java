package dev.rmpedro.appruleta.repositories;

import dev.rmpedro.appruleta.models.entities.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository  extends CrudRepository<Apuesta, Integer> {
   //@Query(value = "select * from ruletas.apuestas a where a.ruleta_id=?1",nativeQuery = true)
    @Query("select a from Apuesta a where a.ruleta.id=?1")
    Iterable<Apuesta> buscarApuestasPorRuletaId(Integer idRuleta);

}
