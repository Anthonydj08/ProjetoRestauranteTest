package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.ItemCardapio;
import model.dao.util.JPAManager;

public class ItemCardapioDaoImpl extends DAOImpl implements ItemCardapioDao{

	@SuppressWarnings("unchecked")
	public List<ItemCardapio> listarTodos(){
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
	
	public ItemCardapio consultarPorNome(String nome) {
		TypedQuery<ItemCardapio> query = manager.createQuery("SELECT ic FROM ItemCardapio ic WHERE ic.nome = :nome",
				ItemCardapio.class);
		query.setParameter("nome", nome);
		ItemCardapio itemCardapio = query.getSingleResult();
		return itemCardapio;
	}
}
