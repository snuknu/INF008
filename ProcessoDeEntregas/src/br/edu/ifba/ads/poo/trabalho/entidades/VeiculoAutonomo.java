package br.edu.ifba.ads.poo.trabalho.entidades;

public abstract class VeiculoAutonomo implements Trajetoria{
	private String matricula;
	private double capacidadeMaxima;
	private Coordenada localizacao;
	
	public VeiculoAutonomo(String matricula, double capacidadeMaxima, Coordenada localizacao) {
		this.setMatricula(matricula);
		this.setCapacidadeMaxima(capacidadeMaxima);
		this.setLocalizacao(localizacao);
	}
	
	public void setLocalizacao(Coordenada localizacao){
		this.localizacao = localizacao;
	}
	
	public void setMatricula(String matricula) { 
		this.matricula = matricula;
	}
	
	public void setCapacidadeMaxima(double capacidadeMaxima) { 
		this.capacidadeMaxima = capacidadeMaxima;
	}
	
	public Coordenada getLocalizacao() {
		return this.localizacao;
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public double getCapacidadeMaxima() {
		return this.capacidadeMaxima;
	}
	
	public abstract double getCustoDeTransporte(double distancia, double peso);

}
