package dev.rmpedro.appruleta.exceptions.handler;



import dev.rmpedro.appruleta.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RuletaHandlerExceptions {

    @ExceptionHandler(value = ApuestasNoRealizadas.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> apuestasNoRealizadas(ApuestasNoRealizadas ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }

    @ExceptionHandler(value = DatosApuestaNoValidos.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> datosApuestaNoValidos(DatosApuestaNoValidos ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }


    @ExceptionHandler(value = RuletaNoExiste.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> ruletaNoExiste(RuletaNoExiste ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }
    @ExceptionHandler(value = RuletaCerradaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> ruletaCerrada(RuletaCerradaException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> missingRequestParameters(MissingServletRequestParameterException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Olvidaste agregar alguno de los siguientes parámetros a la petición");
        response.put("param", ex.getParameterName());
        response.put("paramType", ex.getParameterType());
        return response;
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> paramTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "El tipo de dato que enviaste no coincide con el esperado");
        response.put("param", ex.getName());
        response.put("paramTypeExpected", ex.getParameter().getParameterType());
        response.put("paramTypeGot", ex.getValue().getClass().getName());
        return response;
    }





}
