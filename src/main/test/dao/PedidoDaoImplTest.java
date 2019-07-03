package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Pedido;
import model.dao.util.JPAManager;

public class PedidoDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Pedido> listarTodos() {
		List<Pedido> pedido = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Pedido");
			pedido = query.getResultList();
		} finally {
			mng.close();
		}
		return pedido;
	}

	public Pedido consultarPorNome(String status) {
		try {
			TypedQuery<Pedido> query = manager.createQuery("SELECT p FROM Pedido p WHERE p.status = :status",
					Pedido.class);
			query.setParameter("status", status);
			Pedido pedido = query.getSingleResult();
			return pedido;
		} catch (NoResultException e) {
			return null;
		}
	}
}
