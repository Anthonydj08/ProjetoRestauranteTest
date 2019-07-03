package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Estoque;
import model.dao.util.JPAManager;

public class EstoqueDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Estoque> listarTodos() {
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
		try {
			TypedQuery<Estoque> query = manager.createQuery("SELECT c FROM Estoque c WHERE c.nome = :nome",
					Estoque.class);
			query.setParameter("nome", nome);
			Estoque estoque = query.getSingleResult();
			return estoque;
		} catch (NoResultException e) {
			return null;
		}
	}
}
