package dev.rmpedro.appruleta.services;


import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;
import dev.rmpedro.appruleta.exceptions.ApuestasNoRealizadas;
import dev.rmpedro.appruleta.exceptions.RuletaCerradaException;
import dev.rmpedro.appruleta.exceptions.RuletaNoExiste;
import dev.rmpedro.appruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.rmpedro.appruleta.services.GenerarGanadores.*;


@Service
public class RuletaDAOImpl implements RuletaDAO {
    @Autowired
    ApuestaDAO apuestaDAO;

    protected final RuletaRepository repository;


    public RuletaDAOImpl(RuletaRepository repository) {
        this.repository = repository;

    }


    @Override
    public void guardar(Ruleta ruleta) {

        repository.save(ruleta);
    }

    @Override
    public Ruleta buscarPorId(Integer id) {

        Optional<Ruleta> buscarRuleta = repository.findById(id);
        Ruleta ruleta = null;
        if(buscarRuleta.isPresent()){
            ruleta=buscarRuleta.get();

        }
        else{
            throw new RuletaNoExiste("La ruleta con el ID " + id + " no existe.");
        }
        return ruleta;
    }

    @Override
    public Integer crear() {
        Ruleta ruleta = new Ruleta();
        guardar(ruleta);
        return ruleta.getId();

    }

    @Override
    public Boolean apertura(Integer id) {
    Ruleta ruleta = buscarPorId(id);
    ruleta.setEstaAbierta(true);
    guardar(ruleta);
    return ruleta.getEstaAbierta();

    }

    @Override
    public void apostar(Integer idRuleta, String color, Integer numero, Double monto) {
        Ruleta ruleta = buscarPorId(idRuleta);
        if(ruleta.getEstaAbierta()){
            Apuesta apuesta = new Apuesta();
            apuesta.setColor(Color.valueOf(color.toUpperCase()));
            apuesta.setNumero(numero);
            apuesta.setMonto(monto);

            apuesta.setRuleta(ruleta);
            apuestaDAO.guardar(apuesta);

        }
        else{
            throw new RuletaCerradaException("No es posibles apostar a esta ruleta");
        }



    }



    @Override
    public void girar(Integer id) {
        Ruleta ruleta = buscarPorId(id);
        Integer numero = generarNumeroGanador();
        ruleta.setNumeroGanador(numero);
        ruleta.setColorGanador(generarColorGanador(numero));
        guardar(ruleta);

    }

    @Override
    public Iterable<Apuesta> cierre(Integer id) {
        Ruleta ruleta = buscarPorId(id);
        girar(id);
        Iterable<Apuesta> apuestas = apuestaDAO.calcularResultados(ruleta);
        guardar(ruleta);
        return apuestas;

    }

    @Override
    public Iterable<Ruleta> buscarTodos() {
        return repository.buscar();
    }

    @Override
    public void eliminarPorId(Integer id) {
        repository.deleteById(id);

    }
}
