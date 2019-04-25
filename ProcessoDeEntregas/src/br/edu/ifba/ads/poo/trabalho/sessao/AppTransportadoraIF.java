package br.edu.ifba.ads.poo.trabalho.sessao;

import java.util.ArrayList;
import br.edu.ifba.ads.poo.trabalho.entidades.Entrega;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;
import br.edu.ifba.ads.poo.trabalho.persistencia.TransportadoraDAOIF;



public interface AppTransportadoraIF {
	public void atualizarFrota() throws Exception;
	public void adicionarVeiculoNaFrota(VeiculoAutonomo veiculoAutonomo) throws Exception;
    public VeiculoAutonomo buscarVeiculoDeMenorCusto(Entrega entrega) throws Exception;
    public ArrayList<VeiculoAutonomo> buscarVeiculosPorCapacidade(Entrega entrega) throws Exception;
    public ArrayList<VeiculoAutonomo> getFrota() throws Exception;
	public void atualizarPosicaoDoVeiculo(VeiculoAutonomo veiculoAutonomo, Entrega entrega) throws Exception;    
	public void setTransportadoraDAOIF(TransportadoraDAOIF transportadoraDAO) throws Exception;
}
