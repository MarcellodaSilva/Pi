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

import dao.CompraDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Compra;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CompraService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CompraDao dao;

	public CompraService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void efetuarCompra(Compra compra) throws ValidacaoException {
		validaCompra(compra);
		 dao.adiciona(compra);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarCompra(Compra compra) throws Exception {
		dao.atualiza(compra);
	}
	
	public void validaCompra(Compra compra) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Compra>> violations = validator.validate(compra);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Compra> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
