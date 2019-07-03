package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.ItemPedido;
import model.dao.util.JPAManager;

public class ItemPedidoDaoImpl extends DAOImpl implements ItemPedidoDao {

	@SuppressWarnings("unchecked")
	public List<ItemPedido> listarTodos(){
		List<ItemPedido> itemPedido = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM ItemPedido");
			itemPedido = query.getResultList();
		} finally {
			mng.close();
		}
		return itemPedido;
	}
}
