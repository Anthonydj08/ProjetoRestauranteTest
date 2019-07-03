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
import model.Entidades.Estoque;
import model.Entidades.ItemCardapio;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemCardapioDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		ItemCardapio itemCardapio = new ItemCardapio();
		ItemCardapioDaoImplTest itemCardapioDaoTest = new ItemCardapioDaoImplTest();

		Cardapio cardapio = new Cardapio();
		cardapio.setCategoria("Massas");
		mng.persist(cardapio);
		Estoque estoque = new Estoque();
		estoque.setNome("Teste");
		estoque.setPreco(10);
		estoque.setQuantidade(50);
		estoque.setUniMedida("L");
		mng.persist(estoque);

		itemCardapio.setCardapio(cardapio);
		itemCardapio.setEstoque(estoque);

		itemCardapio.setPreco(20);
		mng.persist(itemCardapio);
		mng.getTransaction().commit();

		ItemCardapio itemCardapioSalvar = itemCardapioDaoTest.consultarPorPreco(20);
		assertEquals(20, 20, itemCardapioSalvar.getPreco());
	}

	@Test
	public void Test2Editar() {
		ItemCardapioDaoImplTest itemCardapioDaoTest = new ItemCardapioDaoImplTest();
		ItemCardapio itemCardapio = itemCardapioDaoTest.consultarPorPreco(20);

		itemCardapio.setPreco(35);
		mng.merge(itemCardapio);
		mng.getTransaction().commit();

		ItemCardapio itemCardapioEditar = itemCardapioDaoTest.consultarPorPreco(35);
		;
		assertEquals(35, itemCardapioEditar.getPreco(), 0);
	}

	@Test
	public void Test3Deletar() {
		ItemCardapioDaoImplTest itemCardapioDaoTest = new ItemCardapioDaoImplTest();
		Cardapio cardapio = new Cardapio();
		cardapio.setCategoria("Massas");
		mng.persist(cardapio);
		Estoque estoque = new Estoque();

		estoque.setNome("testacaray");
		estoque.setPreco(10);
		estoque.setQuantidade(50);
		estoque.setUniMedida("L");
		mng.persist(estoque);

		ItemCardapio itemCardapio = new ItemCardapio();
		itemCardapio.setCardapio(cardapio);
		itemCardapio.setEstoque(estoque);

		itemCardapio.setPreco(20);
		mng.persist(itemCardapio);

		mng.remove(itemCardapio);
		mng.getTransaction().commit();

		ItemCardapio itemCardapioExcluir = itemCardapioDaoTest.consultarPorPreco(20);
		assertNull(itemCardapioExcluir);
	}

	@Test
	public void Test4Listar() {
		ItemCardapioDaoImplTest itemCardapioDaoTest = new ItemCardapioDaoImplTest();

		List<ItemCardapio> itemCardapioLista = itemCardapioDaoTest.listarTodos();
		assertEquals(1, itemCardapioLista.size());
	}

	@After
	public void depois() {
		// mng.getTransaction().rollback();
		mng.close();
	}

}
