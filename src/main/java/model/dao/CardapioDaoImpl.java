package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Cardapio;
import model.dao.util.JPAManager;

public class CardapioDaoImpl extends DAOImpl implements CardapioDao {

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

	public Cardapio consultarPorId(String categoria) {
		TypedQuery<Cardapio> query = manager.createQuery("SELECT c FROM Cardapio c WHERE c.categoria = :categoria",
				Cardapio.class);
		query.setParameter("categoria", categoria);
		Cardapio cardapio = query.getSingleResult();
		return cardapio;
	}
	
	public Cardapio consultarPorNome(String categoria) {
		TypedQuery<Cardapio> query = manager.createQuery("SELECT c FROM Cardapio c WHERE c.categoria = :categoria",
				Cardapio.class);
		query.setParameter("Categoria", categoria);
		Cardapio cardapio = query.getSingleResult();
		return cardapio;
	}
}
