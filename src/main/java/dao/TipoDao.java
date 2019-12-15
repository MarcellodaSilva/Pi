package dao;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.Tipo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TipoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	private Dao<Tipo> dao;
	
	public TipoDao() {}
	
	public TipoDao(EntityManager manager) {
		dao = new Dao<Tipo>(manager, Tipo.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Tipo>(manager, Tipo.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Tipo t) {
		 dao.adiciona(t);	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Tipo t) throws Exception {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Tipo atualiza(Tipo t) throws Exception {
		return dao.atualiza(t);
	}
	
	
	public Tipo buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Tipo t Where t.id = :idTipo";
			Query query = manager.createQuery(sql);
			query.setParameter("idTipo",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
	}
}
