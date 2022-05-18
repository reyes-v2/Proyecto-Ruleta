package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.datos.ApuestaDatosDummy;
import dev.rmpedro.appruleta.datos.RuletaDatosDummy;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.repositories.ApuestaRepository;
import dev.rmpedro.appruleta.repositories.RuletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;



class ApuestaDAOImplTest {

    ApuestaDAO apuestaDAO;
    ApuestaRepository apuestaRepository;

    @BeforeEach
    void setUp(){
        apuestaRepository = mock(ApuestaRepository.class);
        apuestaDAO = new ApuestaDAOImpl(apuestaRepository);
    }






    @Test
    void buscarApuestasPorRuletaId() {

        when(apuestaRepository.buscarApuestasPorRuletaId(1))
                .thenReturn(List.of(ApuestaDatosDummy.apuesta03()));
        List<Apuesta> apuestas = (List<Apuesta>) apuestaDAO.buscarApuestasPorRuletaId(1);

        assertThat(apuestas.size()).isGreaterThan(0);

        verify(apuestaRepository).buscarApuestasPorRuletaId(1);

    }




}