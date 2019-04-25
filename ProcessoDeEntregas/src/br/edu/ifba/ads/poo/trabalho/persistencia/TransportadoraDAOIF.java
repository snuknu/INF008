package br.edu.ifba.ads.poo.trabalho.persistencia;

import java.util.ArrayList;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;

public interface TransportadoraDAOIF {
	
	public void guardeVeiculoAutonomo(VeiculoAutonomo veiculoAutonomo) throws Exception;
	public ArrayList<VeiculoAutonomo> busqueVeiculosAutonomos() throws Exception;
	public void atualizeVeiculoAutonomo(VeiculoAutonomo veiculoAutonomo) throws Exception;
	
}
