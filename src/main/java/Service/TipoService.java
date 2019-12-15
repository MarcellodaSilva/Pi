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

import dao.TipoDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Tipo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TipoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoDao dao;

	public TipoService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarTipo(Tipo tipo) throws Exception {
		validaTipo(tipo);
		 dao.adiciona(tipo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarTipo(Tipo tipo) throws Exception {
		dao.atualiza(tipo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removerTipo(Tipo tipo) throws Exception {
		 dao.remove(tipo);	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerTipoId(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}
	
	public Tipo buscaPorId(Integer id) {
		return dao.buscaPorId(id);	
	}

	public void validaTipo(Tipo tipo) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Tipo>> violations = validator.validate(tipo);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Tipo> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}

}
