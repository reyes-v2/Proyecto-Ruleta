package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ruleta")
public class Controlador {


    @Autowired
    RuletaDAO ruletaDAO;


    @PostMapping ("/crear")
    public ResponseEntity<Integer> crearRuleta(){

        return new ResponseEntity<>(ruletaDAO.crear(), HttpStatus.CREATED);
    }

    @PutMapping("/abrir")
    public ResponseEntity<Boolean> abrirrRuleta(Integer id){

        return new ResponseEntity<>(ruletaDAO.apertura(id),HttpStatus.ACCEPTED);
    }

    @PostMapping("/apostarnumero")
    public ResponseEntity<String> apostarRuleta(Integer id,Integer numero,Double monto){
        ruletaDAO.apostar(id,numero,monto);
        return new ResponseEntity<>("Apuesta creada",HttpStatus.ACCEPTED);

    }
    @PostMapping("/apostarcolor")
    public ResponseEntity<String> apostarRuleta(Integer id, String color,Double monto){
        ruletaDAO.apostar(id,color,monto);
        return new ResponseEntity<>("Apuesta creada",HttpStatus.ACCEPTED);

    }

    @GetMapping("/cierreapuestas")
    public ResponseEntity<Iterable<Apuesta>> cierreApuestas(Integer id){

        return new ResponseEntity<>(ruletaDAO.cierre(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Ruleta>> listarRuletas(){

        return new ResponseEntity<>(ruletaDAO.buscarTodos(),HttpStatus.ACCEPTED);
    }








}
