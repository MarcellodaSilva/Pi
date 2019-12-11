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
import javax.persistence.TypedQuery;
import exception.ValidacaoException;
import model.entity.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProdutoDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	private Dao<Produto> dao;
	
	public ProdutoDao() {}

	public ProdutoDao(EntityManager manager){
		dao = new Dao<Produto>(manager, Produto.class);
	}
	
	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Produto>(manager, Produto.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Produto t) {
		 dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Produto t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Produto atualiza(Produto t) throws Exception {
		return dao.atualiza(t);
	}

	
	public List<Produto> listaTodos() {
		return dao.listaTodos();
	}

	public Produto buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	} 
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		String hql = "DELETE FROM Usuario WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	public List<Produto> pesquisar(String nome) throws ValidacaoException{
		try {
		String hqlproduto = "select  p from  Produto p where lower(p.nome) like :nome";
		TypedQuery<Produto> queryProduto = manager.createQuery(hqlproduto, Produto.class).setParameter("nome",'%'+nome.toLowerCase()+'%');
		List<Produto> produto =	queryProduto.getResultList();
 		return produto;
		}catch(Exception e) {
			e.getMessage();
			throw new ValidacaoException("Deu erro em pesquisar ");
		}
		
	}
	
	public List<Produto> listar() throws ValidacaoException{
		try {
			String hql = "select  p from  Produto";
			TypedQuery<Produto> queryProduto = manager.createQuery(hql, Produto.class);
			List<Produto> produto =	queryProduto.getResultList();
	 		return produto;
			}catch(Exception e) {
				e.getMessage();
				throw new ValidacaoException("Deu erro em listar ");
			}
	}

}
