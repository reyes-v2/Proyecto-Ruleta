package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.exceptions.DatosApuestaNoValidos;
import dev.rmpedro.appruleta.exceptions.RuletaCerradaException;
import dev.rmpedro.appruleta.exceptions.RuletaNoExiste;
import dev.rmpedro.appruleta.mapper.MapperApuesta;
import dev.rmpedro.appruleta.mapper.MapperRuleta;
import dev.rmpedro.appruleta.models.dto.ApuestaDTO;
import dev.rmpedro.appruleta.models.dto.RuletaDTO;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.services.RuletaDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/*TODO:
 * Hacer que el controlador solo reciba un valor de apuesta en String y castear Integers
 * Documentacion y comentarios
 * Administrar excepciones
 * Trabajar en algoritmo generar color y numero ganador
 * Trabajar el algoritmo calcular apuestas
 * JSON ignore properties
 *Investigar notacion para parametros en endpoints
 * DTO
 * */


@RestController
@RequestMapping("api/v1/ruleta")
public class ControladorRuleta {

    @Autowired
    RuletaDAO ruletaDAO;


    @Operation(summary = "Crear nueva ruleta", description = "Crea una nueva ruleta en el sistema y retorna su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ruleta creada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)

    })

    @PostMapping("/crear")
    public ResponseEntity<Integer> crearRuleta() {

        return new ResponseEntity<>(ruletaDAO.crear(), HttpStatus.CREATED);
    }


    @Operation(summary = "Abrir ruleta", description = "Cambie el estado de la ruleta y posteriormente permite la creacion de apuestas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "La ruleta permite nuevas apuestas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content),
            @ApiResponse(responseCode = "404", description = "La ruleta no existe", content = @Content)
    })
    @Parameter(name = "ruletaId", required = true, description = "Identificador unico de ruleta")

    @PutMapping("/abrir")
    public ResponseEntity<Boolean> abrirRuleta(@RequestParam Integer ruletaId) {

        return new ResponseEntity<>(ruletaDAO.apertura(ruletaId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Realizar apuesta", description = "Realiza una nueva apuesta a la ruleta seleccionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Apuesta creada", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApuestaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion Invalida", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "idRuleta", description = "Id de ruleta que permita apuestas", required = true),
            @Parameter(name = "valorApuesta", description = "Apuesta numero: 1-36 o Apuesta color: Rojo o Negro", required = true),
            @Parameter(name = "montoApuesta", description = "Montos menores que 10,000", required = true)
    })

    @PostMapping("/apostar")
    public ResponseEntity<?> apostarRuleta(@RequestParam Integer idRuleta, @RequestParam String valorApuesta, @RequestParam Double montoApuesta) {

        ApuestaDTO apuestaDTO = MapperApuesta.mapApuesta(ruletaDAO.apostar(idRuleta, valorApuesta, montoApuesta));
        return new ResponseEntity<>(apuestaDTO, HttpStatus.ACCEPTED);

    }

    @Operation(summary = "Cierre Apuestas", description = "Calcula todos las apuestas asignadas a una ruleta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Apuestas calculadas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Apuesta.class))}),
            @ApiResponse(responseCode = "400", description = "No hay apuestas que calcular", content = @Content)
    })
    @Parameter(name = "ruletaId", description = "Id de ruleta para calcular sus apuestas")

    @GetMapping("/cerrar")
    public ResponseEntity<Iterable<Apuesta>> cierreApuestas(@RequestParam Integer ruletaId) {

        return new ResponseEntity<>(ruletaDAO.cierre(ruletaId), HttpStatus.ACCEPTED);
    }


    @Operation(summary = "Listar todas las ruletas", description = "Muestra el Id y estado de las ruletas existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Lista de ruletas", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RuletaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "No hay ruletas que mostrar", content = @Content)
    })

    @GetMapping("/listar")
    public ResponseEntity<?> listarRuletas() {
        List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.buscarTodos();
        List<RuletaDTO> ruletasDto = ruletas.
                stream()
                .map(MapperRuleta::mapRuleta)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ruletasDto, HttpStatus.ACCEPTED);
    }


}
