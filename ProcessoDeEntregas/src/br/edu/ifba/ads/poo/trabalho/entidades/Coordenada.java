package br.edu.ifba.ads.poo.trabalho.entidades;

public class Coordenada {
	private double x;
	private double y;
	
	public Coordenada (double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	public double getX() { return this.x; }
	
	public void setX(double x) { this.x = x; }

	public double getY() { return this.y; }
	
	public void setY(double y) { this.y = y; }
}
