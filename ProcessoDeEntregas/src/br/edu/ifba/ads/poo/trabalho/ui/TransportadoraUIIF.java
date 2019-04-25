package br.edu.ifba.ads.poo.trabalho.ui;

import br.edu.ifba.ads.poo.trabalho.sessao.AppTransportadoraIF;

public interface TransportadoraUIIF {
	
	public void exibir() throws Exception;
	public void setLogica(AppTransportadoraIF appTransportadora);

}
