package dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.entity.Cliente;
import model.entity.Compra;
import model.entity.Estoque;
import model.entity.Historico;
import model.entity.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CompraDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	private Dao<Compra> dao;
	
	public CompraDao() {}
	public CompraDao(EntityManager manager){
		dao = new Dao<Compra>(manager, Compra.class);
	}
	
	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Compra>(manager, Compra.class);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Compra t) {
	   dao.adiciona(t);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Compra t) throws Exception {
		dao.remove(t);
	}

	public Compra atualiza(Compra t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Compra> listaTodos() {
		return dao.listaTodos();
	}

	public Compra buscaPorId(int id) {
		return dao.buscaPorId(id);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CompraCliente (Compra compra , List<Cliente> cliente)  {
		compra.setClientelist(cliente);
		dao.adiciona(compra);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CompraEstoque (Compra compra , Estoque estoque) {
		compra.setEstoque(estoque);
		 dao.adiciona(compra);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CompraProduto (Compra compra , Produto produto) {
		compra.setProduto(produto);
		dao.adiciona(compra);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CompraHistorico(Compra t , Historico historico) {
		t.setHistoricoList(historico);
		 dao.adiciona(t);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CompraPassandoProdutoEstoqueClienteHistorico(Compra compra , Produto produto , Estoque estoque , List<Cliente> cliente , Historico historico )  {
		compra.setClientelist(cliente);
		compra.setEstoque(estoque);
		compra.setProduto(produto);
		compra.setHistoricoList(historico);
		dao.adiciona(compra);
	}

}
