package Service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import exception.ValidacaoException;
import model.entity.Cliente;
import model.entity.Farmacia;

@Stateless
public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FarmaciaService farmaciaService;
	@Inject
	private ClienteService clienteService;

	public Object logar(String senha, String login) throws ValidacaoException {
		if (senha.trim().isEmpty() != true && login.trim().isEmpty() != true) {
			Cliente cliente = clienteService.loginCliente(senha, login);
			Farmacia farmacia = farmaciaService.loginFarmacia(senha, login);
			if (cliente != null) {
				return cliente;
			}else if(farmacia != null) {
				return farmacia;
			}

		} else {
			throw new ValidacaoException("Erro de Login");
		}
		return null;

	}

}
