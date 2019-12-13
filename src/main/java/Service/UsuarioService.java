package Service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	@Inject
	private FarmaciaService farmaciaService;

	public UsuarioService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirConta(String senha, String login) {
		try {
			if (senha.trim().isEmpty() != true && login.trim().isEmpty() != true) {
				clienteService.excluirConta(senha, login);
				farmaciaService.excluirConta(senha, login);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
