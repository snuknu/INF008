package br.edu.ifba.ads.poo.trabalho.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifba.ads.poo.trabalho.entidades.Coordenada;
import br.edu.ifba.ads.poo.trabalho.entidades.Drone;
import br.edu.ifba.ads.poo.trabalho.entidades.Terrestre;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;

public class TransportadoraSQLDAO implements TransportadoraDAOIF{

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
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String DB_URI = "jdbc:hsqldb:hsql://localhost/";
	private static final String DB_USER = "SA";
	private static final String DB_PWD = "";
	
	private static final String GUARDAR_VEICULO = "INSERT INTO VEICULO(classe, matricula, capacidade, localizacao_x, localizacao_y) VALUES(?, ?, ?, ?, ?)";
	private static final String BUSCAR_TODOS = "SELECT classe, matricula, capacidade, localizacao_x, localizacao_y  FROM VEICULO";	
	private static final String ATUALIZE_VEICULO = "UPDATE VEICULO SET capacidade = ?, localizacao_x = ?, localizacao_y = ? WHERE matricula = ?";	
	
	public TransportadoraSQLDAO() throws SQLException, ClassNotFoundException {
		Class.forName(TransportadoraSQLDAO.DRIVER_NAME);
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(TransportadoraSQLDAO.DB_URI, 
				TransportadoraSQLDAO.DB_USER, TransportadoraSQLDAO.DB_PWD);
	}
	
	@Override
	public void guardeVeiculoAutonomo(VeiculoAutonomo veiculoAutonomo) throws Exception {
		PreparedStatement stmt = this.getConnection().prepareStatement(TransportadoraSQLDAO.GUARDAR_VEICULO);
		String classe = (veiculoAutonomo instanceof Drone)? "drone" : "terrestre";
		stmt.setString(1, classe);
		stmt.setString(2, veiculoAutonomo.getMatricula());
		stmt.setDouble(3, veiculoAutonomo.getCapacidadeMaxima());
		stmt.setDouble(4, veiculoAutonomo.getLocalizacao().getX());
		stmt.setDouble(5, veiculoAutonomo.getLocalizacao().getY());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public ArrayList<VeiculoAutonomo> busqueVeiculosAutonomos() throws Exception {

		ArrayList<VeiculoAutonomo> veiculos = new ArrayList<VeiculoAutonomo>();
		PreparedStatement stmt = this
							    .getConnection()
								.prepareStatement(TransportadoraSQLDAO.BUSCAR_TODOS);
		ResultSet rSet = stmt.executeQuery();
		Coordenada localizacao;
		while(rSet.next()) {
			if(rSet.getString("classe").equals("terrestre")) {
				
				localizacao = new Coordenada(rSet.getDouble("localizacao_x"), rSet.getDouble("localizacao_x"));
				VeiculoAutonomo veiculoTerrestre = new Terrestre(rSet.getString("matricula"), rSet.getDouble("capacidade"), localizacao);
				veiculos.add(veiculoTerrestre);
				
			}else if(rSet.getString("classe").equals("drone")){
				
				localizacao = new Coordenada(rSet.getDouble("localizacao_x"), rSet.getDouble("localizacao_x"));
				VeiculoAutonomo veiculoTerrestre = new Terrestre(rSet.getString("matricula"), rSet.getDouble("capacidade"), localizacao);
				veiculos.add(veiculoTerrestre);
				
			}else {
				return null;
			}
		}	
		rSet.close();
		stmt.close();
		return veiculos;
	}

	@Override
	public void atualizeVeiculoAutonomo(VeiculoAutonomo veiculoAutonomo) throws Exception {
		PreparedStatement stmt = this.getConnection().prepareStatement(TransportadoraSQLDAO.ATUALIZE_VEICULO);
		stmt.setDouble(1, veiculoAutonomo.getCapacidadeMaxima());
		stmt.setDouble(2, veiculoAutonomo.getLocalizacao().getX());
		stmt.setDouble(3, veiculoAutonomo.getLocalizacao().getY());
		stmt.setString(4, veiculoAutonomo.getMatricula());
		stmt.executeUpdate();
		stmt.close();
	}
}
