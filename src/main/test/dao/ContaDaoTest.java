package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Entidades.Conta;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Conta conta = new Conta();
		ContaDaoImplTest contaDaoTest = new ContaDaoImplTest();

		conta.setStatus("Aberto");
		conta.setValorTotal(200);
		mng.persist(conta);
		mng.getTransaction().commit();

		Conta contaSalvar = contaDaoTest.consultarPorConta("Aberto");
		assertEquals("Aberto", contaSalvar.getStatus());
	}

	@Test
	public void Test2Editar() {
		ContaDaoImplTest contaDaoTest = new ContaDaoImplTest();
		Conta conta = contaDaoTest.consultarPorConta("Aberto");

		conta.setStatus("Fechada");
		conta.setValorTotal(250);
		mng.merge(conta);
		mng.getTransaction().commit();

		Conta contaEditar = contaDaoTest.consultarPorConta("Fechada");
		assertEquals("Fechada", contaEditar.getStatus());
	}

	@Test
	public void Test3Deletar() {
		ContaDaoImplTest contaDaoTest = new ContaDaoImplTest();
		Conta conta = new Conta();

		conta.setStatus("Cancelada");
		conta.setValorTotal(200);
		mng.persist(conta);

		mng.remove(conta);
		mng.getTransaction().commit();

		Conta contaExcluir = contaDaoTest.consultarPorConta("Cancelada");
		assertNull(contaExcluir);
	}

	@Test
	public void Test4Listar() {
		ContaDaoImplTest contaDaoTest = new ContaDaoImplTest();
		
		List<Conta> contaLista = contaDaoTest.listarTodos();
		assertEquals(1, contaLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
