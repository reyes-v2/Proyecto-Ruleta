package dev.rmpedro.appruleta.validaciones;

public class ValidarDatos {

    public static Boolean validarNumeroApuesta(Integer numero,Double monto){
        return (numero >= 0 && numero <= 36) && validarMontoApuesta(monto);

    }
    public static Boolean validarColorApuesta(String color,Double monto){
        return (color.equalsIgnoreCase("rojo" ) ||
                color.equalsIgnoreCase("negro") ) &&
                validarMontoApuesta(monto);

    }
    public static Boolean validarMontoApuesta(Double monto){
        return monto>0 && monto <=10000;

    }


}
