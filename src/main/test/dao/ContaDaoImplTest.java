package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Conta;
import model.dao.util.JPAManager;

public class ContaDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Conta> listarTodos() {
		List<Conta> conta = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Conta");
			conta = query.getResultList();
		} finally {
			mng.close();
		}
		return conta;
	}

	public Conta consultarPorConta(String status) {
		try {
			TypedQuery<Conta> query = manager.createQuery("SELECT c FROM Conta c WHERE c.status = :status",
					Conta.class);
			query.setParameter("status", status);
			Conta conta = query.getSingleResult();
			return conta;
		} catch (NoResultException e) {
			return null;
		}
	}
}
