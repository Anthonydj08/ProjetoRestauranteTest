package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Usuario;
import model.dao.util.JPAManager;

public class UsuarioDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() {
		List<Usuario> usuario = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Usuario");
			usuario = query.getResultList();
		} finally {
			mng.close();
		}
		return usuario;
	}

	public Usuario consultarPorNome(String login) {
		try {
			TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login",
					Usuario.class);
			query.setParameter("login", login);
			Usuario usuario = query.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
}
