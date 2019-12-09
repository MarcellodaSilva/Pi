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
import model.entity.Farmacia;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FarmaciaDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager; 
	private Dao<Farmacia> dao;
	
	
	public FarmaciaDao() {}

	public FarmaciaDao(EntityManager manager){
		dao = new Dao<Farmacia>(manager, Farmacia.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Farmacia>(manager, Farmacia.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Farmacia t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Farmacia t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Farmacia atualiza(Farmacia t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Farmacia> listaTodos() {
		return dao.listaTodos();
	}

	public Farmacia buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
	/*public Farmacia loginFarmacia(String senha , String login)  {
		try {
			String sql = "select f from Farmacia f where f.senha =:senha and f.login =:login";
			TypedQuery<Farmacia> query = manager.createQuery(sql , Farmacia.class);
			query.setParameter("senha",senha);
			query.setParameter("login",login);
			Farmacia farmacia = query.getSingleResult();
			if(farmacia != null && farmacia.getLogin().equals(login) && farmacia.getSenha().equals(senha)) {
				return farmacia;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return null;
	}*/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Farmacia f Where f.id_farmacia = :idFarmacia";
			Query query = manager.createQuery(sql);
			query.setParameter("idFarmacia",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
	}

}
