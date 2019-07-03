package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.Pedido;
import model.dao.util.JPAManager;

public class PedidoDaoImpl extends DAOImpl implements PedidoDao{

	
	@SuppressWarnings("unchecked")
	public List<Pedido> listarTodos(){
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

}
