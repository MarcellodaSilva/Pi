package exception;

import javax.ejb.ApplicationException;

import dto.ViolacoesValidacao;

@ApplicationException(rollback = true)
public class ValidacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private ViolacoesValidacao violacoes;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String mensagem) {
		super(mensagem);
	}

	public ValidacaoException(ViolacoesValidacao violacoes) {
		this.violacoes = violacoes;
	}

	public ViolacoesValidacao getViolacoes() {
		return violacoes;
	}

	public void setViolacoes(ViolacoesValidacao violacoes) {
		this.violacoes = violacoes;
	}
}
