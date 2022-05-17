package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.mapper.MapperRuleta;
import dev.rmpedro.appruleta.models.dto.RuletaDTO;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.services.RuletaDAO;
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

    //Endpoint 1
    @PostMapping ("/crear")
    public ResponseEntity<Integer> crearRuleta(){

        return new ResponseEntity<>(ruletaDAO.crear(), HttpStatus.CREATED);
    }

    //Endpoint 2
    @PutMapping("/abrir")
    public ResponseEntity<Boolean> abrirRuleta(Integer id){

        return new ResponseEntity<>(ruletaDAO.apertura(id), HttpStatus.ACCEPTED);
    }

    //Endpoint 3
    @PostMapping("/apostar")
    public ResponseEntity<?> apostarRuleta(Integer id, String valorApuesta, Double monto){
        Apuesta nuevaApuesta=ruletaDAO.apostar(id,valorApuesta,monto);
        return new ResponseEntity<>(nuevaApuesta,HttpStatus.ACCEPTED);

    }

    //Endpoint 4
    @GetMapping("/cierreapuestas")
    public ResponseEntity<Iterable<Apuesta>> cierreApuestas(Integer id){

        return new ResponseEntity<>(ruletaDAO.cierre(id),HttpStatus.ACCEPTED);
    }


    //Endpoint 5
    @GetMapping(value = "/listar")
    public ResponseEntity<?> listarRuletas(){
        List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.buscarTodos();
        List<RuletaDTO> ruletasDto = ruletas.
                stream()
                .map(MapperRuleta::mapRuleta)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ruletasDto,HttpStatus.ACCEPTED);
    }









}
