package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.ItemCardapio;
import model.dao.util.JPAManager;

public class ItemCardapioDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<ItemCardapio> listarTodos() {
		List<ItemCardapio> itemCardapio = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM ItemCardapio");
			itemCardapio = query.getResultList();
		} finally {
			mng.close();
		}
		return itemCardapio;
	}

	public ItemCardapio consultarPorPreco(double preco) {
		try {
			TypedQuery<ItemCardapio> query = manager.createQuery("SELECT ic FROM ItemCardapio ic WHERE ic.preco = :preco",
					ItemCardapio.class);
			query.setParameter("preco", preco);
			ItemCardapio itemCardapio = query.getSingleResult();
			return itemCardapio;
		} catch (NoResultException e) {
			return null;
		}
	}
}
