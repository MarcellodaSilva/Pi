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
import model.entity.Cliente;
import model.entity.Historico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class HistoricoDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "farmanet")
	private EntityManager manager;
	private Dao<Historico> dao;
	
	public HistoricoDao() {}

	public HistoricoDao(EntityManager manager){
		dao = new Dao<Historico>(manager, Historico.class);
	}
	
	
	@PostConstruct
	private void initDao() {
		this.dao = new Dao<Historico>(manager, Historico.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Historico t)  {
		 dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Historico t) throws Exception {
		dao.remove(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Historico atualiza(Historico t) throws Exception {
		return dao.atualiza(t);
	}

	public List<Historico> listaTodos() {
		return dao.listaTodos();
	}

	public Historico buscaPorId(int id) {
		return dao.buscaPorId(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorID(Integer id) {
		manager.getTransaction().begin();
		try{
			String sql = "Delete From Historico h Where h.id_historico = :idHistorico";
			Query query = manager.createQuery(sql);
			query.setParameter("idHistorico",id);
			query.executeUpdate();
			manager.getTransaction().commit();
			return true;
		}catch(RuntimeException e){
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void HistoricoLCiente (Historico t ,Cliente cliente) {
		t.setCliente(cliente);
		 dao.adiciona(t);
	}

	

}
