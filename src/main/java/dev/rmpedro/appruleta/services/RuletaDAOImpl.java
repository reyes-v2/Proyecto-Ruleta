package dev.rmpedro.appruleta.services;


import dev.rmpedro.appruleta.models.entities.Apuesta;
import dev.rmpedro.appruleta.models.entities.Ruleta;
import dev.rmpedro.appruleta.exceptions.RuletaCerradaException;
import dev.rmpedro.appruleta.exceptions.RuletaNoExiste;
import dev.rmpedro.appruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.rmpedro.appruleta.services.GenerarGanadores.generarColorGanador;
import static dev.rmpedro.appruleta.services.GenerarGanadores.generarNumeroGanador;


@Service
public class RuletaDAOImpl implements RuletaDAO {
   @Autowired
    ApuestaDAO apuestaDAO;


    private final RuletaRepository repository;

    @Autowired
    public RuletaDAOImpl(RuletaRepository repository) {
        this.repository = repository;
    }



    @Override
    public void guardar(Ruleta ruleta) {

        repository.save(ruleta);
    }

    @Override
    public Ruleta buscarPorId(Integer id) {

        Optional<Ruleta> oRuleta = repository.findById(id);
        Ruleta ruletaEncontrada;
        if(oRuleta.isPresent()){
            ruletaEncontrada=oRuleta.get();

        }
        else{
            throw new RuletaNoExiste("La ruleta con el ID " + id + " no existe.");
        }
        return ruletaEncontrada;
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
    public Apuesta apostar(Integer idRuleta, String valorApuesta, Double monto) {
        Ruleta ruleta = buscarPorId(idRuleta);
        Apuesta nuevaApuesta;
        if(ruleta.getEstaAbierta()){
          nuevaApuesta=apuestaDAO.crearApuesta(valorApuesta,monto,ruleta);

        }
        else{
            throw new RuletaCerradaException("No es posibles apostar a esta ruleta");
        }
        return nuevaApuesta;


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
        Iterable<Apuesta> apuestasCalculadas = apuestaDAO.calcularResultados(ruleta);
        guardar(ruleta);
        return apuestasCalculadas;

    }

    @Override
    public Iterable<Ruleta> buscarTodos() {
        Iterable<Ruleta> ruletas = repository.findAll();
        if(((List<Ruleta>)ruletas).isEmpty()){
            throw new RuletaNoExiste("No existen ruletas que mostrar");
        }
        return ruletas;
    }


}
