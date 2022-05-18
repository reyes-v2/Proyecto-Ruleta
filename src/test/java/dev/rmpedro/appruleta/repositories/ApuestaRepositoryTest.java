package dev.rmpedro.appruleta.repositories;

import dev.rmpedro.appruleta.datos.ApuestaDatosDummy;
import dev.rmpedro.appruleta.datos.RuletaDatosDummy;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApuestaRepositoryTest {


    @Autowired
    private ApuestaRepository repository;

    @Autowired
    private RuletaRepository ruletaRepository;


    @BeforeEach
    void setUp(){
        ruletaRepository.save(RuletaDatosDummy.ruleta01());
        repository.save(ApuestaDatosDummy.apuesta01());
        repository.save(ApuestaDatosDummy.apuesta02());
        repository.save(ApuestaDatosDummy.apuesta03());


    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
    @Test
    void buscarPorRuletaId() {


        List<Apuesta> apuestas = (List<Apuesta>) repository.buscarApuestasPorRuletaId(1);
        assertThat(apuestas.size()).isGreaterThan(0);



    }
}