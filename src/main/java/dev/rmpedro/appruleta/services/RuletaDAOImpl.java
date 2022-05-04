package dev.rmpedro.appruleta.services;


import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.ApostarColor;
import dev.rmpedro.appruleta.exceptions.ApuestasNoRealizadas;
import dev.rmpedro.appruleta.exceptions.RuletaCerradaException;
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
        return ruleta;
    }

    @Override
    public Integer crear() {
        Ruleta ruleta = new Ruleta();
        ruleta.setEstaAbierta(true);
        guardar(ruleta);
        return ruleta.getId();

    }

    @Override
    public Boolean estado(Integer id) {
    Ruleta ruleta = buscarPorId(id);
    return ruleta.getEstaAbierta();

    }

    @Override
    public void apostar(Integer id, ApostarColor color, Integer numero, Double monto) {
        Ruleta ruleta = buscarPorId(id);
        if(ruleta.getEstaAbierta()){
            Apuesta apuesta = new Apuesta();
            apuesta.setColor(color);
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

        List<Apuesta> apuestas = (List<Apuesta>) apuestaDAO.buscarApuestas(id);
        if(apuestas.isEmpty()){
            throw new ApuestasNoRealizadas("No hay apuestas para calcular");

        }
        else{
            ruleta.setEstaAbierta(false);
            guardar(ruleta);
        for(Apuesta aps : apuestas){
            aps.setEsGanadora(false);
            aps.setPremio(0d);
            if(aps.getColor()!=null){
                if(aps.getColor()==ruleta.getColorGanador()){
                    aps.setEsGanadora(true);
                    aps.setPremio(aps.getMonto()*2);
                    apuestaDAO.guardar(aps);
                }
            }
            else{
                if(aps.getNumero()== ruleta.getNumeroGanador()){
                    aps.setEsGanadora(true);
                    aps.setPremio(aps.getMonto()*36);
                    apuestaDAO.guardar(aps);

                }

            }
        }}
        return apuestas;



    }

    @Override
    public Iterable<Ruleta> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id) {
        repository.deleteById(id);

    }
}
