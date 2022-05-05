package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;
import dev.rmpedro.appruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ruleta")
public class Controlador {


    @Autowired
    RuletaDAO ruletaDAO;


    @PostMapping ("/crear")
    public Integer crearRuleta(){

        return ruletaDAO.crear();
    }

    @PutMapping("/abrir")
    public Boolean abrirrRuleta(Integer id){

        return ruletaDAO.apertura(id);
    }

    @PostMapping("/apostar")
    public String apostarRuleta(Integer id, String color,Integer numero,Double monto){
        ruletaDAO.apostar(id,color,numero,monto);
        return "Apuesta creada";

    }

    @GetMapping("/cierreapuestas")
    public Iterable<Apuesta> cierreApuestas(Integer id){

        return ruletaDAO.cierre(id);
    }

    @GetMapping("/listar")
    public Iterable<Ruleta> listarRuletas(){
        return ruletaDAO.buscarTodos();
    }








}
