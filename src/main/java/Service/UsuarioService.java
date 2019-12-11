package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.UsuarioDao;
import exception.ValidacaoException;
import model.entity.Usuario;

@Stateless
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Inject
	private UsuarioDao dao;
	
	public UsuarioService() {
	}
	
	public Usuario logar(String senha, String login) throws ValidacaoException {
		return dao.logar(senha, login);
	}
	
	public void excluirConta(String senha, String login) {
		dao.excluirConta(senha, login);
	}
}
