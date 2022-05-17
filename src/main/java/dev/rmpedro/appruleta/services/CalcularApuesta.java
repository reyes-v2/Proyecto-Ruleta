package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.enums.TipoApuesta;

public class CalcularApuesta {

    public static Apuesta calcularApuesta(Ruleta ruleta, Apuesta apuesta){
        if (apuesta.getTipoApuesta()== TipoApuesta.COLOR) {
            if (apuesta.getValorApuesta().equalsIgnoreCase(ruleta.getColorGanador().toString())) {
                apuesta.setEsGanadora(true);
                apuesta.setPremio(apuesta.getMonto() * 2);

            }
        } else {
            if (Integer.parseInt(apuesta.getValorApuesta())== ruleta.getNumeroGanador()) {
                apuesta.setEsGanadora(true);
                apuesta.setPremio(apuesta.getMonto() * 36);

            }

        }
        return apuesta;

    }
}
