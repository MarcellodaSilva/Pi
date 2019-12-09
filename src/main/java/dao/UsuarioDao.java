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
import model.entity.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	
	private Dao<Usuario> dao;
	
	public UsuarioDao(){}
	
	public UsuarioDao(EntityManager manager){
		dao = new Dao<Usuario>(manager, Usuario.class);
	}
	
	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Usuario>(manager, Usuario.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Usuario t)  {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Usuario t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario atualiza(Usuario t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Usuario> listaTodos() {
		return dao.listaTodos();
	}

	public Usuario buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Usuario u Where u.id_usuario = :idUsuario";
			Query query = manager.createQuery(sql);
			query.setParameter("idUsuario",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
		
	}

	public Object logar(String senha , String login)  {
		try {
			Object user;
			String sql = "select c from Cliente c where c.senha =:senha and c.login =:login";
			TypedQuery<Object> query = manager.createQuery(sql , Object.class);
			query.setParameter("senha",senha);
			query.setParameter("login",login);
			user = query.getSingleResult();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return null;
			
	}
}
