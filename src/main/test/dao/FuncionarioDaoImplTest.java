package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Funcionario;
import model.dao.util.JPAManager;

public class FuncionarioDaoImplTest extends DaoImplTest {

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos() {
		List<Funcionario> pessoa = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Funcionario");
			pessoa = query.getResultList();
		} finally {
			mng.close();
		}
		return pessoa;
	}

	public Funcionario consultarPorNome(String nome) {
		try {
			TypedQuery<Funcionario> query = manager.createQuery("SELECT p FROM Funcionario p WHERE p.nome = :nome",
					Funcionario.class);
			query.setParameter("nome", nome);
			Funcionario funcionario = query.getSingleResult();
			return funcionario;
		} catch (NoResultException e) {
			return null;
		}
	}
}
