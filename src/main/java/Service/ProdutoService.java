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
import dao.ProdutoDao;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.entity.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDao dao;

	public ProdutoService() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarProduto(Produto produto) throws Exception {
		validaProduto(produto);
		dao.adiciona(produto);
	}

	public List<Produto> listarProdutos() {
		return dao.listaTodos();
	}

	public Produto getProduto(Integer id) {
		return dao.buscaPorId(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarProduto(Produto produto) throws Exception {
		dao.atualiza(produto);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarCampos(Integer id, Produto produto) throws Exception {
		Produto produtodoBanco = dao.buscaPorId(id);
		produtodoBanco.atualizarDados(produto);
		dao.atualiza(produtodoBanco);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removerProduto(Integer id) throws Exception {
		dao.removePorID(id);
	}
	public List<Produto>listar() throws ValidacaoException{
		return dao.listar();
	}
        
        public List<Produto>pesquisar(String produto) throws ValidacaoException{
          return dao.pesquisar(produto);
           
        }

	public void validaProduto(Produto produto) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

		if (violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();

			for (ConstraintViolation<Produto> vi : violations) {
				mensagens.add(vi.getMessage());

			}
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}

}
