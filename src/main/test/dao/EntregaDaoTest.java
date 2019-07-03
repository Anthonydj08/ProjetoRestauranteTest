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

import model.Entidades.Entrega;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntregaDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Entrega entrega = new Entrega();
		EntregaDaoImplTest entregaDaoTest = new EntregaDaoImplTest();
		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua 35");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.persist(entrega);
		mng.getTransaction().commit();

		Entrega entregaSalvar = entregaDaoTest.consultarPorRua("Rua 35");
		assertEquals("Rua 35", entregaSalvar.getRua());
	}

	@Test
	public void Test2Editar() {
		EntregaDaoImplTest entregaDaoTest = new EntregaDaoImplTest();
		Entrega entrega = entregaDaoTest.consultarPorRua("Rua 35");

		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua chupe chupe");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.merge(entrega);
		mng.getTransaction().commit();

		Entrega entregaEditar = entregaDaoTest.consultarPorRua("Rua chupe chupe");
		assertEquals("Rua chupe chupe", entregaEditar.getRua());
	}

	@Test
	public void Test3Deletar() {
		EntregaDaoImplTest entregaDaoTest = new EntregaDaoImplTest();
		Entrega entrega = new Entrega();

		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua da madeira");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.persist(entrega);

		mng.remove(entrega);
		mng.getTransaction().commit();

		Entrega entregaExcluir = entregaDaoTest.consultarPorRua("Rua da madeira");
		assertNull(entregaExcluir);
	}

	@Test
	public void Test4Listar() {
		EntregaDaoImplTest entregaDaoTest = new EntregaDaoImplTest();
		
		List<Entrega> entregaLista = entregaDaoTest.listarTodos();
		assertEquals(1, entregaLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
