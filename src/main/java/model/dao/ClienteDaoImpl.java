package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Entidades.Cliente;
import model.dao.util.JPAManager;

public class ClienteDaoImpl extends DAOImpl implements ClienteDao{
	
	@SuppressWarnings("unchecked")
	public List<Cliente> ListarTodos(){
		List<Cliente> cliente = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM Cliente");
			cliente = query.getResultList();
		} finally {
			mng.close();
		}
		return cliente;
	}

	public Cliente consultarPorCpf(String cpf) {
		TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf",
				Cliente.class);
		query.setParameter("cpf", cpf);
		Cliente cliente = query.getSingleResult();
		return cliente;
	}
}
