package dev.rmpedro.appruleta.datos;

import dev.rmpedro.appruleta.enums.TipoApuesta;
import dev.rmpedro.appruleta.models.entities.Apuesta;

public class ApuestaDatosDummy {


    public static Apuesta apuesta01(){
        Apuesta apuesta = new Apuesta(1,"negro", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(RuletaDatosDummy.ruleta01());
        return apuesta;
    }
    public static Apuesta apuesta02(){
        return new Apuesta(2,"rojo", TipoApuesta.COLOR,7000d);
    }
    public static Apuesta apuesta03(){
        Apuesta apuesta = new Apuesta(3,"rojo", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(RuletaDatosDummy.ruleta01());
        return apuesta;
    }
    public static Apuesta apuesta04(){
        Apuesta apuesta = new Apuesta(1,"negro", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(RuletaDatosDummy.ruleta02());
        apuesta.setEsGanadora(true);
        apuesta.setPremio(16000d);

        return apuesta;
    }
    public static String apuesta01Json() {
        return "{" + "\"id\": " + 1 + "," +
                "\"valorApuesta\": \"NEGRO\", " +
                "\"tipoApuesta\": \"COLOR\", " +
                "\"monto\": " + 8000.0 + "," +
                "\"idRuletaApuesta\": " + 1 + "}";
    }

}
