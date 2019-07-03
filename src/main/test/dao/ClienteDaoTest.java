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

import model.Entidades.Cliente;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Cliente cliente = new Cliente();
		ClienteDaoImplTest clienteDaoTest = new ClienteDaoImplTest();

		cliente.setNome("Maria");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		
		mng.persist(cliente);
		mng.getTransaction().commit();

		Cliente clienteSalvar = clienteDaoTest.consultarPorNome("Maria");
		assertEquals("Maria", clienteSalvar.getNome());
	}

	@Test
	public void Test2Editar() {
		ClienteDaoImplTest clienteDaoTest = new ClienteDaoImplTest();
		Cliente cliente = clienteDaoTest.consultarPorNome("Maria");

		cliente.setNome("Joao");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		mng.merge(cliente);
		mng.getTransaction().commit();

		Cliente clienteEditar = clienteDaoTest.consultarPorNome("Joao");
		assertEquals("Joao", clienteEditar.getNome());
	}

	@Test
	public void Test3Deletar() {
		ClienteDaoImplTest clienteDaoTest = new ClienteDaoImplTest();
		Cliente cliente = new Cliente();

		cliente.setNome("Peste");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		mng.persist(cliente);

		mng.remove(cliente);
		mng.getTransaction().commit();

		Cliente clienteExcluir = clienteDaoTest.consultarPorNome("Peste");
		assertNull(clienteExcluir);
	}

	@Test
	public void Test4Listar() {
		ClienteDaoImplTest clienteDaoTest = new ClienteDaoImplTest();
		
		List<Cliente> clienteLista = clienteDaoTest.listarTodos();
		assertEquals(1, clienteLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
