package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.datos.RuletaDatosDummy;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.repositories.ApuestaRepository;
import dev.rmpedro.appruleta.repositories.RuletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuletaDAOImplTest {

    RuletaDAO ruletaDAO;
    RuletaRepository ruletaRepository;

    @BeforeEach
    void setUp(){
        ruletaRepository = mock(RuletaRepository.class);
        ruletaDAO = new RuletaDAOImpl(ruletaRepository);
    }



    @Test
    void buscarPorId() {
        when(ruletaRepository.findById(1)).thenReturn(Optional.of(RuletaDatosDummy.ruleta01()));
        Ruleta ruleta = ruletaDAO.buscarPorId(1);
        assertThat(ruleta.getId()).isEqualTo(1);
        verify(ruletaRepository).findById(1);
    }
}