package br.edu.ifba.ads.poo.trabalho.sessao;

import java.util.ArrayList;

import br.edu.ifba.ads.poo.trabalho.entidades.Entrega;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;
import br.edu.ifba.ads.poo.trabalho.persistencia.TransportadoraDAOIF;

public class AppTransportadora implements AppTransportadoraIF{
	
	private TransportadoraDAOIF transportadoraDAO;
	private ArrayList<VeiculoAutonomo> frota;

	public AppTransportadora() {
		this.frota = new ArrayList<VeiculoAutonomo>();
	}
	
	@Override
	public void setTransportadoraDAOIF(TransportadoraDAOIF transportadoraDAO) {
		this.transportadoraDAO = transportadoraDAO;
	}
	
	/**
	 * A propiedade transportadoraDAO precisa ser setada antes.
	 */
	@Override
	public void atualizarFrota() throws Exception {
		this.frota = transportadoraDAO.busqueVeiculosAutonomos();
	}

	@Override
	public void adicionarVeiculoNaFrota(VeiculoAutonomo veiculoAutonomo) 
			throws Exception {
		transportadoraDAO.guardeVeiculoAutonomo(veiculoAutonomo);
		this.atualizarFrota();
	}
	
	@Override
	public void atualizarPosicaoDoVeiculo( VeiculoAutonomo veiculoAutonomo, Entrega entrega) throws Exception {
		veiculoAutonomo.setLocalizacao(entrega.getDestino());
		this.transportadoraDAO.atualizeVeiculoAutonomo(veiculoAutonomo);
		this.atualizarFrota();
	}
	
	@Override
	public ArrayList<VeiculoAutonomo> getFrota() {
		return this.frota;
	}
	
	@Override
	public VeiculoAutonomo buscarVeiculoDeMenorCusto(Entrega entrega) 
			throws Exception {
		
		ArrayList<VeiculoAutonomo> veiculosCapazes = buscarVeiculosPorCapacidade(entrega);
		VeiculoAutonomo veiculoDeMenorCusto = veiculosCapazes.get(0);
		
		double distancia = veiculoDeMenorCusto.getDistancia(
				entrega.getOrigem(), entrega.getDestino());
		double custo = veiculoDeMenorCusto.getCustoDeTransporte(
				distancia, entrega.getPesoCarga());
		
		double custoCorrente;
		for(int i=1; i<(veiculosCapazes.size()-1); i++) {
			distancia = veiculosCapazes.get(i).getDistancia(
					entrega.getOrigem(), entrega.getDestino());
			custoCorrente = veiculosCapazes.get(i)
					.getCustoDeTransporte(distancia, entrega.getPesoCarga());
			if(custo > custoCorrente){
				veiculoDeMenorCusto = veiculosCapazes.get(i);
				custo = custoCorrente;
			}
		}
		return veiculoDeMenorCusto;
	}

	@Override
	public ArrayList<VeiculoAutonomo> buscarVeiculosPorCapacidade(Entrega entrega) throws Exception {
		ArrayList<VeiculoAutonomo> veiculosCapazes = 
				new ArrayList<VeiculoAutonomo>();
		for(VeiculoAutonomo veiculo : this.getFrota()) {
			if(veiculo.getCapacidadeMaxima() >= entrega.getPesoCarga())
				veiculosCapazes.add(veiculo);
		}
		return veiculosCapazes;
	}

}
