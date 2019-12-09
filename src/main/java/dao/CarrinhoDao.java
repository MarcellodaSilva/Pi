package dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import exception.ValidacaoException;
import model.entity.Carrinho;
import model.entity.Estoque;
import model.entity.Historico;
import model.entity.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CarrinhoDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager; 
	private Dao<Carrinho> dao;
	
	public CarrinhoDao() {}

	public CarrinhoDao(EntityManager manager){
		dao = new Dao<Carrinho>(manager, Carrinho.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Carrinho>(manager, Carrinho.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Carrinho t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Carrinho t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Carrinho atualiza(Carrinho t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Carrinho> listaTodos() {
		return dao.listaTodos();
	}

	public Carrinho buscaPorId(int id) {
		return dao.buscaPorId(id);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean adicionaProdutoCarrinho (List<Produto> produto , Carrinho carrinho) throws ValidacaoException {
		try{
			carrinho.setProduto(produto);
			dao.adiciona(carrinho);
			return true;
		}catch(Exception e) {
			throw new ValidacaoException ("Erro pra adicionar produto em carrinho");
		}
		
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverProduto(List<Produto> produto) throws ValidacaoException {
		Query query;
		int modificados = 0;
		try {
		for (int i = 0; i < produto.size(); i++) {
			String sql = "DELETE FROM carrinho_produto WHERE id_produto = :id";
			query = manager.createQuery(sql);
			query.setParameter("id", produto.get(i).getIdProduto());
			modificados = query.executeUpdate();
		}
		}catch (Exception e) {
			e.getMessage();
			throw new ValidacaoException ("Erro pra remover produto em carrinho");
		}
		if(modificados > 0) return true;
		else return false;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CarrinhoEstoque (Carrinho t , List<Estoque> estoque) {
		t.setEstoque(estoque);
		 dao.adiciona(t);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CarrinhoHistorico(Carrinho t, Historico historico)  {
		t.setHistorico(historico);
		dao.adiciona(t);
	}

}