package dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.Marca;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MarcaDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	private Dao<Marca> dao;
	
	public MarcaDao() {}
	
	public MarcaDao(EntityManager manager) {
		dao = new Dao<Marca>(manager, Marca.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Marca>(manager, Marca.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Marca t) {
		 dao.adiciona(t);	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Marca t) throws Exception {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Marca atualiza(Marca t) throws Exception {
		return dao.atualiza(t);
	}
	
	
	public Marca buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Marca m Where m.id = :idMarca";
			Query query = manager.createQuery(sql);
			query.setParameter("idMarca",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
	}
}
