package model.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;

public class JPAManager {

	private static JPAManager manager;
	private SessionFactory sessionFactory;

	private JPAManager() {
		sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("ProjetoRestaurante");
	}

	public static JPAManager getInstance() {
		if (manager == null) {
			manager = new JPAManager();
		}

		return manager;
	}

	public EntityManager getEntityManager() {
		return sessionFactory.createEntityManager();
	}
}