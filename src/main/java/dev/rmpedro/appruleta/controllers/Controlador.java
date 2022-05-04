package dev.rmpedro.appruleta.controllers;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;
import dev.rmpedro.appruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ruleta")
public class Controlador {
    private final RuletaDAO ruletaDAO;
    @Autowired
    public Controlador(RuletaDAO ruletaDAO) {
        this.ruletaDAO=ruletaDAO;
    }

    @GetMapping("/crear")
    public Integer crearRuleta(){

        return ruletaDAO.crear();
    }

    @GetMapping("/abrir")
    public Boolean abrirrRuleta(Integer id){

        return ruletaDAO.estado(id);
    }
    @GetMapping("/apostar")
    public String apostarRuleta(Integer id, String color,Integer numero,Double monto){
        ruletaDAO.apostar(id,Color.valueOf(color.toUpperCase()),numero,monto);
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
