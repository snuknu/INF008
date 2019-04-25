package br.edu.ifba.ads.poo.trabalho.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifba.ads.poo.trabalho.entidades.Coordenada;
import br.edu.ifba.ads.poo.trabalho.entidades.Drone;
import br.edu.ifba.ads.poo.trabalho.entidades.Terrestre;
import br.edu.ifba.ads.poo.trabalho.entidades.Entrega;
import br.edu.ifba.ads.poo.trabalho.entidades.VeiculoAutonomo;
import br.edu.ifba.ads.poo.trabalho.persistencia.TransportadoraSQLDAO;
import br.edu.ifba.ads.poo.trabalho.sessao.AppTransportadora;
import br.edu.ifba.ads.poo.trabalho.sessao.AppTransportadoraIF;

public class TransportadoraUISwing extends JFrame implements ActionListener, TransportadoraUIIF{

	public JButton btSelecione;

	private JLabel coletaX;
	private JLabel coletaY;
	private JLabel entregaX;
	private JLabel entregaY;
	private JLabel pesoDaCarga;
	
	private JTextField txtColetaX;
	private JTextField txtColetaY;
	private JTextField txtEntregaX;
	private JTextField txtEntregaY;
	private JTextField txtPesoDaCarga;
	
	private AppTransportadoraIF app;	

	public TransportadoraUISwing() throws Exception {
		super();
		app = new AppTransportadora();
		TransportadoraSQLDAO transportadoraSQLDAO = new TransportadoraSQLDAO();
		app.setTransportadoraDAOIF(transportadoraSQLDAO);
		app.atualizarFrota();
	}
	
	@Override
	public void setLogica(AppTransportadoraIF appTransportadora) {
		this.app = appTransportadora;
	}
	
	
	public void asm() throws Exception {
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(5, 2));
		this.coletaX = new JLabel("Coleta X: ");
		this.coletaY = new JLabel("Coleta Y: ");
		this.entregaX = new JLabel("Entrega X: ");
		this.entregaY = new JLabel("Entrega Y: ");
		this.pesoDaCarga = new JLabel("Peso da Carga: ");

		this.txtColetaX = new JTextField();
		this.txtColetaY = new JTextField();
		this.txtEntregaX = new JTextField();
		this.txtEntregaY = new JTextField();
		this.txtPesoDaCarga = new JTextField();
		
		center.add(coletaX);	center.add(txtColetaX);	
		center.add(coletaY);	center.add(txtColetaY);
		center.add(entregaX);	center.add(txtEntregaX);
		center.add(entregaY);	center.add(txtEntregaY);
		center.add(pesoDaCarga);center.add(txtPesoDaCarga);
		
		this.setSize(500, 300);
		this.setTitle("Transportadora");
		this.getContentPane().setLayout(new BorderLayout());
		this.btSelecione = new JButton("SELECIONAR O MELHOR TRANSPORTE");
		this.btSelecione.addActionListener(this);
		this.getContentPane().add(btSelecione, BorderLayout.SOUTH);
		this.getContentPane().add(center, BorderLayout.CENTER);

	}


	private void showInfo() throws NumberFormatException, Exception {
		String message;
		Entrega entrega;
		Coordenada pontoColeta = 
				new Coordenada(	Double.parseDouble( this.txtColetaX.getText() ), 
								Double.parseDouble( this.txtColetaY.getText() ));
		Coordenada pontoEntrega = 
				new Coordenada( Double.parseDouble( this.txtEntregaX.getText() ), 
								Double.parseDouble( this.txtEntregaY.getText() ));
		
		double pesoCarga = Double.parseDouble( this.txtPesoDaCarga.getText() );
		entrega = new Entrega("e1", pontoColeta, pontoEntrega, pesoCarga);
		VeiculoAutonomo veiculoDeMenorCusto= this.app.buscarVeiculoDeMenorCusto(entrega);
		String tipoDeTransporte = (veiculoDeMenorCusto instanceof Drone)? "Drone" : "Tranporte terrestre";

		double distancia = veiculoDeMenorCusto.getDistancia(pontoColeta, pontoEntrega);
		double custo = veiculoDeMenorCusto.getCustoDeTransporte(distancia, pesoCarga);
		
		message = 	"Tipo de transporte: " + tipoDeTransporte + "\n" +
				"Matricula: " + veiculoDeMenorCusto.getMatricula() + "\n" +
				"Custo: R$ " + custo + "\n";

		JOptionPane.showMessageDialog(this, message, "Custo", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) throws Exception, ClassNotFoundException, IOException, SQLException {
		TransportadoraUISwing appSwing = new TransportadoraUISwing();
		appSwing.exibir();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.showInfo();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void exibir() throws Exception {
		this.asm();
		this.setVisible(true);
	}

}
