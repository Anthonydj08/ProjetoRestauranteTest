package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.Conta;
import model.dao.util.JPAManager;

public class ContaDaoImpl extends DAOImpl implements ContaDao{

	@SuppressWarnings("unchecked")
	public List<Conta> listarTodos(){
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

}
