package br.edu.ifba.ads.poo.trabalho.entidades;

public class Terrestre extends VeiculoAutonomo{

	public Terrestre(String matricula, double capacidadeMaxima, Coordenada localizacao) {
		super(matricula, capacidadeMaxima, localizacao);
	}

	@Override
	public double getCustoDeTransporte(double distancia, double peso) {
		double valorMinimo = 30;
		double custo = distancia * 1 * peso;
		return (custo<valorMinimo) ? valorMinimo : custo;
	}

	@Override
	public double getDistancia(Coordenada origem, Coordenada destino) {
		// Distância de Manhattan
		return 	Math.abs(origem.getX() - destino.getX()) +
				Math.abs(origem.getY() - destino.getY());
		
	}

}
