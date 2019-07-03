package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Funcionario;
import model.dao.util.JPAManager;

public class FuncionarioDaoImpl extends DAOImpl implements FuncionarioDao{
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos(){
		List<Funcionario> funcionario = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Funcionario");
			funcionario = query.getResultList();
		} finally {
			mng.close();
		}
		return funcionario;
	}
	
	public Funcionario consultarPorCpf(String cpf) {
		TypedQuery<Funcionario> query = manager.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = :cpf",
				Funcionario.class);
		query.setParameter("cpf", cpf);
		Funcionario funcionario = query.getSingleResult();
		return funcionario;
	}
	
}
