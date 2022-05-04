package dev.rmpedro.appruleta.utils;


import dev.rmpedro.appruleta.entities.Apuesta;
import dev.rmpedro.appruleta.entities.Ruleta;
import dev.rmpedro.appruleta.enums.ApostarColor;
import dev.rmpedro.appruleta.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class GenericoComandos implements CommandLineRunner {

	@Autowired
	private dev.rmpedro.appruleta.services.RuletaDAO ruletaDAO;
	@Autowired
	private dev.rmpedro.appruleta.services.ApuestaDAO apuestaDAO;
	@Override
	public void run(String... args) throws Exception {

		/*Integer id =ruletaDAO.crear();
		System.out.println("id ruleta= " + id);
		Boolean estado = ruletaDAO.estado(id);
		System.out.println("estado ruleta= " + estado);*/
	/*	ruletaDAO.apostar(12, ApostarColor.ROJO,null,7000d);

		ruletaDAO.apostar(12, ApostarColor.NEGRO,null,7000d);
		//ruletaDAO.asignarApuestas(id);
	//	ruletaDAO.girar(id);
		System.out.println("Apuestas realizadas");
		Iterable<Apuesta> apuestas= ruletaDAO.cierre(12);
		for(Apuesta apuesta:apuestas){
			System.out.println("apuesta = " + apuesta);
		}*/
		/*System.out.println("Total de ruletas");
		Iterable<Ruleta> ruletas=ruletaDAO.buscarTodos();
		for (Ruleta ruleta :ruletas ) {
			System.out.println("ruleta = " + ruleta);
		}*/

		Ruleta ruleta = ruletaDAO.buscarPorId(12);
		ruleta.setEstaAbierta(false);
		ruletaDAO.guardar(ruleta);
		ruletaDAO.apostar(12, null,12,7000d);




		
	}

}
