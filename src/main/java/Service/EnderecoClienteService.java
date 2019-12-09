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
import dao.EnderecoClienteDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.EnderecoCliente;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnderecoClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoClienteDao dao;

	public EnderecoClienteService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarEndereco(EnderecoCliente enderecoCliente) throws Exception {
		validaEndereco(enderecoCliente);
		 dao.adiciona(enderecoCliente);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEnderecoFarmacia(EnderecoCliente enderecoCliente) throws Exception {
		dao.atualiza(enderecoCliente);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarCampos(Integer id, EnderecoCliente enderecoCliente) throws Exception {
		EnderecoCliente enderecoClientedoBanco = dao.buscaPorId(id);
		enderecoClientedoBanco.atualizarDados(enderecoCliente);
		dao.atualiza(enderecoClientedoBanco);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerEnderecoCliente(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}

	public void validaEndereco(EnderecoCliente enderecoCliente) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<EnderecoCliente>> violations = validator.validate(enderecoCliente);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<EnderecoCliente> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}

}
