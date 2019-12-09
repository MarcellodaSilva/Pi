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
import dao.EnderecoFarmaciaDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.EnderecoFarmacia;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnderecoFarmaciaService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EnderecoFarmaciaDao dao;

	public EnderecoFarmaciaService() {}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarEndereco(EnderecoFarmacia enderecoFarmacia) throws Exception {
		validaEndereco(enderecoFarmacia);
		dao.adiciona(enderecoFarmacia);
	} 
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEnderecoFarmacia(EnderecoFarmacia enderecoFarmacia) throws Exception {
		dao.atualiza(enderecoFarmacia);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarCampos(Integer id, EnderecoFarmacia enderecoFarmacia) throws Exception {
		EnderecoFarmacia enderecoFarmaciadoBanco = dao.buscaPorId(id);
		enderecoFarmaciadoBanco.atualizarDados(enderecoFarmacia);
		dao.atualiza(enderecoFarmaciadoBanco);
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerEnderecoFarmacia(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}
	
	public void validaEndereco(EnderecoFarmacia enderecoFarmacia) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<EnderecoFarmacia>> violations = validator.validate(enderecoFarmacia);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<EnderecoFarmacia> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
