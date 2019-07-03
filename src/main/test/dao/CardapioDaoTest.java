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

import model.Entidades.Cardapio;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardapioDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Cardapio cardapio = new Cardapio();
		CardapioDaoImplTest cardapioDaoTest = new CardapioDaoImplTest();

		cardapio.setCategoria("Massas");
		mng.persist(cardapio);
		mng.getTransaction().commit();

		Cardapio cardapioSalvar = cardapioDaoTest.consultarPorNome("Massas");
		assertEquals("Massas", cardapioSalvar.getCategoria());
	}

	@Test
	public void Test2Editar() {
		CardapioDaoImplTest cardapioDaoTest = new CardapioDaoImplTest();
		Cardapio cardapio = cardapioDaoTest.consultarPorNome("Massas");

		cardapio.setCategoria("Entrada");
		mng.merge(cardapio);
		mng.getTransaction().commit();

		Cardapio cardapioEditar = cardapioDaoTest.consultarPorNome("Entrada");
		assertEquals("Entrada", cardapioEditar.getCategoria());
	}

	@Test
	public void Test3Deletar() {
		CardapioDaoImplTest cardapioDaoTest = new CardapioDaoImplTest();
		Cardapio cardapio = new Cardapio();

		cardapio.setCategoria("Teste");
		mng.persist(cardapio);

		mng.remove(cardapio);
		mng.getTransaction().commit();

		Cardapio cardapioExcluir = cardapioDaoTest.consultarPorNome("Teste");
		assertNull(cardapioExcluir);
	}

	@Test
	public void Test4Listar() {
		CardapioDaoImplTest cardapioDaoTest = new CardapioDaoImplTest();
		
		List<Cardapio> cardapioLista = cardapioDaoTest.listarTodos();
		assertEquals(1, cardapioLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
