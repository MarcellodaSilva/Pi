package Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import dao.EstoqueDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Estoque;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstoqueDao dao;

	public EstoqueService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarEstoque(Estoque estoque) throws Exception {
		validaEstoque(estoque);
		dao.adiciona(estoque);
	}

	public List<Estoque> listarEstoque() {
		return dao.listaTodos();
	}

	public Estoque getCliente(Integer id) {
		return dao.buscaPorId(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEstoque(Estoque estoque) throws Exception {
		dao.atualiza(estoque);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerEstoque(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}

	public void validaEstoque(Estoque estoque) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Estoque>> violations = validator.validate(estoque);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Estoque> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
