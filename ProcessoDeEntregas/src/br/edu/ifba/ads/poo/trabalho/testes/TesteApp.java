
package br.edu.ifba.ads.poo.trabalho.testes;

import java.util.ArrayList;

import br.edu.ifba.ads.poo.trabalho.entidades.Coordenada;
import br.edu.ifba.ads.poo.trabalho.entidades.Drone;
import br.edu.ifba.ads.poo.trabalho.entidades.Entrega;
import br.edu.ifba.ads.poo.trabalho.entidades.Terrestre;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;
import br.edu.ifba.ads.poo.trabalho.persistencia.TransportadoraSQLDAO;
import br.edu.ifba.ads.poo.trabalho.sessao.AppTransportadora;

/**
 * 	Crie a tablela antes de usar a classe de teste
 * 
 *		classe varchar
 *		matricula varchar
 *		capacidade float
 *		localizacao_x float
 *		localizacao_y float
 * 
 * */

public class TesteApp {
	
	public void testarAddVeiculos() throws Exception {
		
		AppTransportadora transportadora = new AppTransportadora();
		
		TransportadoraSQLDAO transportadoraSQLDAO = new TransportadoraSQLDAO();
		
		transportadora.setTransportadoraDAOIF(transportadoraSQLDAO);

		transportadora.atualizarFrota();
		
		System.out.println("\n######## CLASSE DE TESTE   ########");

		
		
		/**
		 * Testa adicao de veiculos 
		 */
		Drone d1 = new Drone("d1", 10, new Coordenada(0, 0));
		Drone d2 = new Drone("d2", 20, new Coordenada(5, 10));
		Drone d3 = new Drone("d3", 30, new Coordenada(20, 35));
		Terrestre t1 = new Terrestre("t1", 10, new Coordenada(70, 80));
		Terrestre t2 = new Terrestre("t2", 20, new Coordenada(0, 200));
		Terrestre t3 = new Terrestre("t3", 30, new Coordenada(90, 30));	
		
		System.out.println("\nTESTE DE ADICAO");		
		if(transportadora.getFrota().size()==0) {
			transportadora.adicionarVeiculoNaFrota(d1);
			transportadora.adicionarVeiculoNaFrota(d2);
			transportadora.adicionarVeiculoNaFrota(d3);
			transportadora.adicionarVeiculoNaFrota(t1);
			transportadora.adicionarVeiculoNaFrota(t2);
			transportadora.adicionarVeiculoNaFrota(t3);
			System.out.println("\nADICAO REALIZADA");
		}else {
			System.out.println("Ja existem registros no Banco, adicao desnecessaria");
		}
		
		System.out.println("\nCARREGAMENTO INICIAL");
		for(VeiculoAutonomo veiculo : transportadora.getFrota()) {
			System.out.println(
					"Tipo: "+ ((veiculo instanceof Drone)?"Drone":"Terrestre") +" "+
					"Matricula: "+ veiculo.getMatricula() + " " + 
					"Capacidade: "+ veiculo.getCapacidadeMaxima() +" "+ 
					"Localizacao X: "+ veiculo.getLocalizacao().getX()+" "+
					"Localizacao Y: "+  veiculo.getLocalizacao().getY()
			);

		}
		
		/**
		 * Testa retorno de veiculos com capacidade de levar a carga 
		 */
		Entrega entrega1;
		Coordenada destino = new Coordenada(45,250);
		Coordenada origem = d1.getLocalizacao();
		double pesoCarga = 20;
		
		entrega1 = new Entrega("e1", origem, destino , pesoCarga);
		
		ArrayList<VeiculoAutonomo> veiculosCapazes =  transportadora.buscarVeiculosPorCapacidade(entrega1);
		
		System.out.println("\nVEICULOS CAPAZES");
		for(VeiculoAutonomo veiculo : veiculosCapazes) {
			System.out.println(
					"Tipo: "+ ((veiculo instanceof Drone)?"Drone":"Terrestre") +" "+
					"Matricula: "+ veiculo.getMatricula() + " " + 
					"Capacidade: "+ veiculo.getCapacidadeMaxima() +" "+ 
					"Localizacao X: "+ veiculo.getLocalizacao().getX()+" "+
					"Localizacao Y: "+  veiculo.getLocalizacao().getY()
			);
		}
		
		
		
		/**
		 * Testa retorno de veiculos com capacidade de levar a carga 
		 */
		Entrega entrega2;
		destino = new Coordenada(80,40);
		origem = d2.getLocalizacao();
		pesoCarga = 30;
		entrega2 = new Entrega("e2", origem, destino , pesoCarga);
		
		VeiculoAutonomo veiculoMenorCusto =  transportadora.buscarVeiculoDeMenorCusto(entrega2);
		
		System.out.println("\nVEICULO DE MENOR CUSTO");
		System.out.println(
				"Tipo: "+ ((veiculoMenorCusto instanceof Drone)?"Drone":"Terrestre") +" "+
				"Matricula: "+ veiculoMenorCusto.getMatricula() + " " + 
				"Capacidade: "+ veiculoMenorCusto.getCapacidadeMaxima() +" "+ 
				"Localizacao X: "+ veiculoMenorCusto.getLocalizacao().getX()+" "+
				"Localizacao Y: "+  veiculoMenorCusto.getLocalizacao().getY()
		);

		
	}
	
    public static void main(String[] args) throws Exception{
        (new TesteApp()).testarAddVeiculos();
    }  
}
