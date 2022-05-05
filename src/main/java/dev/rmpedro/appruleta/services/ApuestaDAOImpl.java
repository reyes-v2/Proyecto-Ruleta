package dev.rmpedro.appruleta.services;

import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.Color;
import dev.rmpedro.appruleta.exceptions.ApuestasNoRealizadas;
import dev.rmpedro.appruleta.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApuestaDAOImpl implements ApuestaDAO{

        protected final ApuestaRepository repository;

        public ApuestaDAOImpl(ApuestaRepository repository) {
                this.repository = repository;

        }


        @Override
        public Optional<Apuesta> buscarPorId(Integer id) {
                return Optional.empty();
        }

        @Override
        public Apuesta guardar(Apuesta entidad) {
                return repository.save(entidad);
        }

        @Override
        public Iterable<Apuesta> buscarApuestas(Integer id) {
                return repository.buscar(id);
        }

        @Override
        public void eliminarPorId(Integer id) {

        }

        @Override
        public Iterable<Apuesta> calcularResultados(Ruleta ruleta) {
                //Ruleta ruleta = ruletaDAO.buscarPorId(id);
                List<Apuesta> apuestas = (List<Apuesta>) buscarApuestas(ruleta.getId());
                if (apuestas.isEmpty()) {
                        throw new ApuestasNoRealizadas("No hay apuestas para calcular");

                } else {
                        ruleta.setEstaAbierta(false);
                        for (Apuesta apuesta : apuestas) {
                                if (apuesta.getColor() != null) {
                                        if (apuesta.getColor() == ruleta.getColorGanador()) {
                                                apuesta.setEsGanadora(true);
                                                apuesta.setPremio(apuesta.getMonto() * 2);
                                                guardar(apuesta);
                                        }
                                } else {
                                        if (apuesta.getNumero() == ruleta.getNumeroGanador()) {
                                                apuesta.setEsGanadora(true);
                                                apuesta.setPremio(apuesta.getMonto() * 36);
                                                guardar(apuesta);

                                        }

                                }
                        }
                }
                return apuestas;

        }

        @Override
        public void crearApuesta(String color, Double monto,Ruleta ruleta) {
                Apuesta apuesta = new Apuesta();
                apuesta.setColor(Color.valueOf(color.toUpperCase()));
                apuesta.setMonto(monto);
               apuesta.setRuleta(ruleta);
               guardar(apuesta);
        }

        @Override
        public void crearApuesta(Integer numero, Double monto,Ruleta ruleta) {
                Apuesta apuesta = new Apuesta();
                apuesta.setNumero(numero);
                apuesta.setMonto(monto);
                apuesta.setRuleta(ruleta);
                guardar(apuesta);
        }
}
