package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Cliente;
import model.dao.util.JPAManager;

public class ClienteDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodos() {
		List<Cliente> pessoa = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Cliente");
			pessoa = query.getResultList();
		} finally {
			mng.close();
		}
		return pessoa;
	}

	public Cliente consultarPorNome(String nome) {
		try {
			TypedQuery<Cliente> query = manager.createQuery("SELECT p FROM Cliente p WHERE p.nome = :nome",
					Cliente.class);
			query.setParameter("nome", nome);
			Cliente cliente = query.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
}
