package dev.rmpedro.appruleta.datos;

import dev.rmpedro.appruleta.enums.TipoApuesta;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;

public class ApuestaDatosDummy {


    public static Apuesta apuesta01(){
        Apuesta apuesta = new Apuesta(1,"negro", TipoApuesta.COLOR,8000d);
        apuesta.setRuleta(RuletaDatosDummy.ruleta01());
        return apuesta;
    }
    public static Apuesta apuesta02(){
        Apuesta apuesta = new Apuesta(2,"rojo", TipoApuesta.COLOR,7000d);
        return apuesta;
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
        StringBuilder builder = new StringBuilder("{");
        builder.append("\"id\": " + 1 + ",");
        builder.append("\"valorApuesta\": \"NEGRO\", ");
        builder.append("\"tipoApuesta\": \"COLOR\", ");
        builder.append("\"monto\": " + 8000.0+",");
        builder.append("\"idRuletaApuesta\": " + 1 + "}");
        return builder.toString();
    }

}
