package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Cardapio;
import model.dao.util.JPAManager;

public class CardapioDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Cardapio> listarTodos() {
		List<Cardapio> cardapio = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Cardapio");
			cardapio = query.getResultList();
		} finally {
			mng.close();
		}
		return cardapio;
	}

	public Cardapio consultarPorNome(String categoria) {
		try {
			TypedQuery<Cardapio> query = manager.createQuery("SELECT c FROM Cardapio c WHERE c.categoria = :categoria",
					Cardapio.class);
			query.setParameter("categoria", categoria);
			Cardapio cardapio = query.getSingleResult();
			return cardapio;
		} catch (NoResultException e) {
			return null;
		}
	}
}
