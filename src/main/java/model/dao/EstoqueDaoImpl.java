package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Estoque;
import model.dao.util.JPAManager;

public class EstoqueDaoImpl extends DAOImpl implements EstoqueDao{

	@SuppressWarnings("unchecked")
	public List<Estoque> listarTodos(){
		List<Estoque> estoque = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Estoque");
			estoque = query.getResultList();
		} finally {
			mng.close();
		}
		return estoque;
	}

	public Estoque consultarPorNome(String nome) {
		TypedQuery<Estoque> query = manager.createQuery("SELECT e FROM Estoque e WHERE e.nome = :nome",
				Estoque.class);
		query.setParameter("nome", nome);
		Estoque estoque = query.getSingleResult();
		return estoque;
	}
}
