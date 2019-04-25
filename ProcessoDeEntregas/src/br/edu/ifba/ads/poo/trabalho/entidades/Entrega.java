package br.edu.ifba.ads.poo.trabalho.entidades;


public class Entrega{
	private String numeroDeOrdem;
	private Coordenada origem;
	private Coordenada destino;
	private double pesoCarga;
	
	
	public Entrega(String numeroDeOrdem, 
			Coordenada origem, 
			Coordenada destino, double pesoCarga) {
		this.numeroDeOrdem = numeroDeOrdem;
		this.origem = origem;
		this.destino = destino;
		this.pesoCarga = pesoCarga;
	}

	public String getNumeroDeOrdem() {
		return this.numeroDeOrdem;
	}
	
	public double getPesoCarga() {
		return this.pesoCarga;
	}
	
	public Coordenada getOrigem() {
		return this.origem;
	}
	
	public Coordenada getDestino() {
		return this.destino;
	}
	
}
