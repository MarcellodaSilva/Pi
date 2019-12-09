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
import dao.HistoricoDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Historico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class HistoricoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricoDao dao;
	
	public HistoricoService(){}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarHistorico(Historico historico) throws Exception {
		validaHistorico(historico);
		dao.adiciona(historico);
	}
	
	public List<Historico> listarHistorico() {
		return dao.listaTodos();
	}
	
	public Historico getHistorico(Integer id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarHistorico(Historico historico) throws Exception {
		dao.atualiza(historico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerHistorico(Integer id) {
		boolean result = dao.removePorID(id);
		return result;
	}
	
	public void validaHistorico(Historico historico) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Historico>> violations = validator.validate(historico);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Historico> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
