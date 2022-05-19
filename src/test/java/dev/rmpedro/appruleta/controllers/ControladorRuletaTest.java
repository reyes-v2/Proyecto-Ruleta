package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.datos.ApuestaDatosDummy;
import dev.rmpedro.appruleta.datos.RuletaDatosDummy;
import dev.rmpedro.appruleta.services.RuletaDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ControladorRuleta.class)
class ControladorRuletaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("ruletaDaoImpl")
    private RuletaDAO ruletaDAO;


    @Test
    @DisplayName("Retorna el id del nuevo objeto creado y un estado 'created'")
    void crearRuleta() throws Exception{
        when(ruletaDAO.crear()).thenReturn(RuletaDatosDummy.ruleta01().getId());
        mockMvc.perform(post("/api/v1/ruleta/crear").accept(MediaType.APPLICATION_JSON)
                .content(RuletaDatosDummy.ruletaIdJson01())).andExpect(status().isCreated());
        verify(ruletaDAO).crear();


    }

    @Test
    @DisplayName("Abre la ruleta y retorna un mensaje de confirmacion")
    void abrirRuleta() throws Exception{
        when(ruletaDAO.apertura(1))
                .thenReturn("La ruleta esta disponible para apuestas");
        mockMvc.perform(put("/api/v1/ruleta/abrir/?ruletaId=1").accept(MediaType.APPLICATION_JSON)
                        .content(RuletaDatosDummy.ruletaEstadoJson01()))
                .andExpect(status().isAccepted());
        verify(ruletaDAO).apertura(1);


    }


    @Test
    @DisplayName("Retorna la apuesta creada y un estado 'Acepted'")
    void apostarRuleta() throws Exception{
        when(ruletaDAO.apostar(1,"negro",8000d)).thenReturn(ApuestaDatosDummy.apuesta01());
        mockMvc.perform(post("/api/v1/ruleta/apostar/?idRuleta=1&valorApuesta=negro&montoApuesta=8000")
                .accept(MediaType.APPLICATION_JSON).content(ApuestaDatosDummy.apuesta01Json()))
                .andExpect(status().isAccepted());

    }
    @Test
    @DisplayName("Retorna una lista de las ruletas creadas y un estado 'Accepted'")
    void listarRuletas() throws Exception {
        when(ruletaDAO.buscarTodos()).thenReturn(List.of(RuletaDatosDummy.ruleta01()));

        mockMvc.perform(get("/api/v1/ruleta/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(ruletaDAO).buscarTodos();
    }

    @Test
    @DisplayName("Retorna una lista de apuestas calculas y un estado 'Accepted'")
    void cierreApuestas() throws Exception{
        when(ruletaDAO.cierre(2)).thenReturn(List.of(ApuestaDatosDummy.apuesta04()));
        mockMvc.perform(get("/api/v1/ruleta/cerrar?ruletaId=2").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted());
        verify(ruletaDAO).cierre(2);

    }



}