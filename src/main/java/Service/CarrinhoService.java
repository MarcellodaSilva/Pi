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

import dao.CarrinhoDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Carrinho;
import model.entity.Produto;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CarrinhoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CarrinhoDao dao;

	public CarrinhoService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void comprarCarrinho(Carrinho carrinho) throws Exception {
		validaCarrinho(carrinho);
		dao.adiciona(carrinho);
	
	}
	

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removerCarrinho (Carrinho carrinho) throws Exception {
		validaCarrinho(carrinho);
		dao.remove(carrinho);
	}
	public void adicionarProduto(List<Produto> produto , Carrinho carrinho) throws ValidacaoException {
		dao.adicionaProdutoCarrinho(produto, carrinho);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerProduto (List<Produto> produto) throws ValidacaoException {
		return dao.RemoverProduto(produto);
	
	}

	public void validaCarrinho(Carrinho carrinho) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Carrinho>> violations = validator.validate(carrinho);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Carrinho> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
