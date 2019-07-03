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

import model.Entidades.Estoque;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstoqueDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Estoque estoque = new Estoque();
		EstoqueDaoImplTest estoqueDaoTest = new EstoqueDaoImplTest();

		estoque.setNome("Pizza");
		estoque.setPreco(10);
		estoque.setQuantidade(50);
		estoque.setUniMedida("L");
		mng.persist(estoque);
		mng.getTransaction().commit();

		Estoque estoqueSalvar = estoqueDaoTest.consultarPorNome("Pizza");
		assertEquals("Pizza", estoqueSalvar.getNome());
	}

	@Test
	public void Test2Editar() {
		EstoqueDaoImplTest estoqueDaoTest = new EstoqueDaoImplTest();
		Estoque estoque = estoqueDaoTest.consultarPorNome("Pizza");

		estoque.setNome("Bebidas");
		estoque.setPreco(10);
		estoque.setQuantidade(50);
		estoque.setUniMedida("L");
		mng.merge(estoque);
		mng.getTransaction().commit();

		Estoque estoqueEditar = estoqueDaoTest.consultarPorNome("Bebidas");
		assertEquals("Bebidas", estoqueEditar.getNome());
	}

	@Test
	public void Test3Deletar() {
		EstoqueDaoImplTest estoqueDaoTest = new EstoqueDaoImplTest();
		Estoque estoque = new Estoque();

		estoque.setNome("Teste");
		estoque.setPreco(10);
		estoque.setQuantidade(50);
		estoque.setUniMedida("L");
		mng.persist(estoque);

		mng.remove(estoque);
		mng.getTransaction().commit();

		Estoque estoqueExcluir = estoqueDaoTest.consultarPorNome("Teste");
		assertNull(estoqueExcluir);
	}

	@Test
	public void Test4Listar() {
		EstoqueDaoImplTest estoqueDaoTest = new EstoqueDaoImplTest();
		
		List<Estoque> estoqueLista = estoqueDaoTest.listarTodos();
		assertEquals(1, estoqueLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
