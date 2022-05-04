package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.ApostarColor;
import dev.rmpedro.appruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerarGanadores {


    public static Integer generarNumeroGanador(){
        Random random  = new Random();
        return random.nextInt(37);


    }
    public static ApostarColor generarColorGanador(Integer numero){
        ApostarColor color;
        List<Integer> coloresNegros = (Arrays.asList(2,4,6,8,10,11,13,15,17,20,
                22,24,26,28,29,31,33,35));
        if(coloresNegros.contains(numero)){
            color=ApostarColor.NEGRO;

        }
        else if(numero==0){
            color = ApostarColor.VERDE;
        }
        else {
            color=ApostarColor.ROJO;
        }
        return color;




    }
}
