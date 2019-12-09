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
import dao.FarmaciaDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Farmacia;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FarmaciaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FarmaciaDao dao;

	public FarmaciaService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarFarmacia(Farmacia farmacia) throws Exception {
		validaFarmacia(farmacia);
		dao.adiciona(farmacia);
	}

	public List<Farmacia> listarClientes() {
		return dao.listaTodos();
	}

	public Farmacia getFarmacia(Integer id) {
		return dao.buscaPorId(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarFarmacia(Farmacia farmacia) throws Exception {
		dao.atualiza(farmacia);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerFarmacia(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}

	/*public Farmacia loginFarmacia(String senha, String login) {
		return dao.loginFarmacia(senha, login);
	}*/
	
	public void validaFarmacia(Farmacia farmacia) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Farmacia>> violations = validator.validate(farmacia);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Farmacia> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
