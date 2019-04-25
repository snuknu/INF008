package br.edu.ifba.ads.poo.trabalho.entidades;

public class Drone extends VeiculoAutonomo{
	
	public Drone(String matricula, double capacidadeMaxima, Coordenada localizacao) {
		super(matricula, capacidadeMaxima, localizacao);
	}

	@Override
	public double getCustoDeTransporte(double distancia, double peso) {
		double valorMinimo = 40;
		double custo = distancia * 1.25 * peso;
		return (custo<valorMinimo) ? valorMinimo : custo;
	}

	@Override
	public double getDistancia(Coordenada origem, Coordenada destino) {
		//Distância Euclidiana
		return
			Math.abs(
				Math.sqrt( 
					Math.pow((destino.getX()-origem.getX()), 2) + 
					Math.pow((destino.getY()-origem.getY()), 2)
				)
			);
	}

}
