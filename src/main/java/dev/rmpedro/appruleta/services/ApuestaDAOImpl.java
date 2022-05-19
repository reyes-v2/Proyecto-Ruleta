package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.exceptions.RuletaCerradaException;
import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.enums.TipoApuesta;
import dev.rmpedro.appruleta.exceptions.ApuestasNoRealizadas;
import dev.rmpedro.appruleta.exceptions.DatosApuestaNoValidos;
import dev.rmpedro.appruleta.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static dev.rmpedro.appruleta.validaciones.ValidarDatos.*;
import static dev.rmpedro.appruleta.services.CalcularApuesta.calcularApuesta;

@Service
public class ApuestaDAOImpl implements ApuestaDAO {


    private final ApuestaRepository repository;

    @Autowired
    public ApuestaDAOImpl(ApuestaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Apuesta guardar(Apuesta entidad) {

        return repository.save(entidad);
    }

    @Override
    public Iterable<Apuesta> buscarApuestasPorRuletaId(Integer id) {

        return repository.buscarApuestasPorRuletaId(id);
    }


    @Override
    public Apuesta crearApuesta(String valorApuesta, Double monto, Ruleta ruleta) {
        Apuesta apuestaGuardada;
        if (validarColorApuesta(valorApuesta) && validarMontoApuesta(monto)) {
            Apuesta nuevaApuesta = new Apuesta(valorApuesta.toUpperCase(), TipoApuesta.COLOR, monto, ruleta);
            apuestaGuardada = guardar(nuevaApuesta);
        } else {
            try {
                Integer numeroApuesta = Integer.parseInt(valorApuesta);
                if (validarNumeroApuesta(numeroApuesta) && validarMontoApuesta(monto)) {
                    Apuesta nuevaApuesta = new Apuesta(valorApuesta, TipoApuesta.NUMERO, monto, ruleta);
                    apuestaGuardada = guardar(nuevaApuesta);
                } else {
                    throw new DatosApuestaNoValidos("No es posible apostar a este valor");
                }

            } catch (NumberFormatException e) {
                throw new DatosApuestaNoValidos("No es posible apostar a este valor");
            }
        }
        return apuestaGuardada;
    }

    @Override
    public Iterable<Apuesta> calcularResultados(Ruleta ruleta) {
        if( ruleta.getEstaAbierta()==null || !ruleta.getEstaAbierta()){
            throw new RuletaCerradaException("Esta ruleta ya fue calculada o aun no esta abierta");
        }
        List<Apuesta> apuestas = (List<Apuesta>) buscarApuestasPorRuletaId(ruleta.getId());
        if (apuestas.isEmpty()) {
            throw new ApuestasNoRealizadas("No hay apuestas para calcular");

        } else{
            ruleta.setEstaAbierta(false);
            apuestas.forEach(apuesta -> guardar(calcularApuesta(ruleta, apuesta)));
        }

        return apuestas;

    }


}
