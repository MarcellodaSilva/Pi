package Service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ClienteDao;
import dao.FarmaciaDao;
import exception.ValidacaoException;
import model.entity.Cliente;
import model.entity.Farmacia;
import model.entity.Usuario;

@Stateless
public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	public Usuario logar(String senha, String login) throws ValidacaoException {
		if (senha.trim().isEmpty() != true && login.trim().isEmpty() != true) {
			Usuario usuario = usuarioService.logar(senha, login);
			if (usuario != null) {
				return usuario;
			} 		

		} else {
			throw new ValidacaoException("Erro de Login");
		}
		return null;

	}

}
