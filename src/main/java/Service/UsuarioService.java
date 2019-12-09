package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.UsuarioDao;
import model.entity.Usuario;

@Stateless
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao dao;

	public UsuarioService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarUsuario(Usuario usuario) throws Exception {
		dao.adiciona(usuario);
	}

	public List<Usuario> listarUsuario() {
		return dao.listaTodos();
	}

	public Usuario getUsuario(Integer id) {
		return dao.buscaPorId(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarUsuario(Usuario usuario) throws Exception {
		dao.atualiza(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerUsuario(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}
}
