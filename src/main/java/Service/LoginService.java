package Service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ClienteDao;
import dao.FarmaciaDao;
import exception.ValidacaoException;
import model.entity.Cliente;
import model.entity.Farmacia;

@Stateless
public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDao Daocliente;
	@Inject
	private FarmaciaDao Daofarmacia;

	/*public Object logar(String senha, String login) throws ValidacaoException {
		if (senha.trim().isEmpty() != true && login.trim().isEmpty() != true) {
			Cliente cliente = Daocliente.loginCliente(senha, login);
			Farmacia farmacia = Daofarmacia.loginFarmacia(senha, login);
			if (cliente != null) {
				return cliente;
			} else if (farmacia != null) {
				return farmacia;
			} else {
				return null;
			}

		} else {
			throw new ValidacaoException("Erro de Login");
		}

	}*/

}
