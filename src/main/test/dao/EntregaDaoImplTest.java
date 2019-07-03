package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Entrega;
import model.dao.util.JPAManager;

public class EntregaDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Entrega> listarTodos() {
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

	public Entrega consultarPorRua(String rua) {
		try {
			TypedQuery<Entrega> query = manager.createQuery("SELECT e FROM Entrega e WHERE e.rua = :rua",
					Entrega.class);
			query.setParameter("rua", rua);
			Entrega entrega = query.getSingleResult();
			return entrega;
		} catch (NoResultException e) {
			return null;
		}
	}
}
