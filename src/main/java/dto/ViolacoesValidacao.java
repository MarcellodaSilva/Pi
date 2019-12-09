package dto;

import java.util.List;

public class ViolacoesValidacao {
	
	private List<String> mensagens;
	
	public ViolacoesValidacao(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

}
