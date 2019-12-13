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
import model.entity.Cliente;
import model.entity.Compra;
import model.entity.Farmacia;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager; 
	
	private Dao<Cliente> dao;
	
	public ClienteDao() {}
	
	public ClienteDao(EntityManager manager){
		dao = new Dao<Cliente>(manager, Cliente.class);
	}
	
	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Cliente>(manager, Cliente.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Cliente t)  {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Cliente t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente atualiza(Cliente t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}

	public Cliente buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Cliente c Where c.id = :idCliente";
			Query query = manager.createQuery(sql);
			query.setParameter("idCliente",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Cliente loginCliente(String senha , String login)  {
		try {
			String sql = "select c from Cliente c where c.senha =:senha and c.login =:login";
			TypedQuery<Cliente> query = manager.createQuery(sql , Cliente.class);
			query.setParameter("senha",senha);
			query.setParameter("login",login);
			Cliente cliente = query.getSingleResult();
			if(cliente != null && cliente.getLogin().equals(login) && cliente.getSenha().equals(senha)) {
				return cliente;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirConta(String senha, String login) {
		try {
			String hql = "Delete from Cliente c where c.senha =: senha and c.login=: login";
			Query query = manager.createQuery(hql);
			query.setParameter("senha", senha);
			query.setParameter("login", login);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void clienteCompra (Cliente t , List<Compra> compra) {
		t.setCompralist(compra);
		dao.adiciona(t);
	}
	public Cliente editarCliente (Cliente t) {
		
		
		return null;
	}
	
	

}
