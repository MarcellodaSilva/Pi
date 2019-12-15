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

import dao.MarcaDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Marca;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MarcaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MarcaDao dao;

	public MarcaService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarMarca(Marca marca) throws Exception {
		validaMarca(marca);
		 dao.adiciona(marca);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarMarca(Marca marca) throws Exception {
		dao.atualiza(marca);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removerMarca(Marca marca) throws Exception {
		 dao.remove(marca);	
	}
	
	public Marca buscaPorId(Integer id) {
		return dao.buscaPorId(id);	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerMarcaId(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}

	public void validaMarca(Marca marca) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Marca>> violations = validator.validate(marca);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Marca> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
