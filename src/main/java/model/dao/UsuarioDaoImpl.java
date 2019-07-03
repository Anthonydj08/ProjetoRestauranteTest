package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Usuario;
import model.dao.util.JPAManager;

public class UsuarioDaoImpl extends DAOImpl implements UsuarioDao {

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() {
		List<Usuario> usuario = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			mng.getTransaction().begin();
			Query query = mng.createQuery("FROM Usuario");
			usuario = query.getResultList();
		} catch (Exception e) {
			mng.getTransaction().rollback();
		} finally {
			mng.close();
		}
		return usuario;
	}

	public Usuario consultarPorLogin(String login) {
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login",
				Usuario.class);
		query.setParameter("login", login);
		Usuario usuario = query.getSingleResult();
		return usuario;
	}

	public Usuario consultarLogin(String usuario, String senha) {
		TypedQuery<Usuario> query = manager
				.createQuery(" SELECT a FROM Usuario a WHERE a.login = :usuario AND a.senha = :senha", Usuario.class);
		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);
		Usuario obj = query.getSingleResult();
		return obj;
	}

}
