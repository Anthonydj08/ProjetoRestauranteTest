package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.Entrega;
import model.dao.util.JPAManager;

public class EntregaDaoImpl extends DAOImpl implements EntregaDao{

	@SuppressWarnings("unchecked")
	public List<Entrega> listarTodos(){
		List<Entrega> entrega = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Entrega");
			entrega = query.getResultList();
		} finally {
			mng.close();
		}
		return entrega;
	}
}
