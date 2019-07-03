package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.ItemPedido;
import model.dao.util.JPAManager;

public class DAOImpl implements DAO<Object> {

	EntityManager manager = JPAManager.getInstance().getEntityManager();

	public void insert(Object obj) {
		try {
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public void insertItens(List<ItemPedido> itensPedido) throws Exception {
		try {
			manager.getTransaction().begin();
			for (Object o : itensPedido) {
				manager.persist(o);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public void update(Object obj) {
		try {
			manager.getTransaction().begin();
			manager.merge(obj);
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public void remove(Object obj) {
		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(obj));
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public Object findbyID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> findporid(Integer id) {
		List<Object> obj = null;
		try {
			Query query = (Query) manager.find(getClass(), id);
			obj = query.getResultList();
		} finally {
			manager.close();
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<Object> findALL() {
		List<Object> lista = null;
		try {
			lista = manager.createQuery("FROM " + Object.class.getName()).getResultList();
		} finally {
			manager.close();
		}
		return lista;
	}

}
