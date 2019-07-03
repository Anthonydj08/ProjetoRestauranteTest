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

import model.Entidades.Funcionario;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Funcionario funcionario = new Funcionario();
		FuncionarioDaoImplTest funcionarioDaoTest = new FuncionarioDaoImplTest();

		funcionario.setNome("Maria");
		funcionario.setCpf("25225154865");
		funcionario.setEmail("maria@gmail.com");
		funcionario.setFuncao("Recepcionista");
		funcionario.setNumCarteira(1556498);
		funcionario.setSalario(1250.00);
		funcionario.setTelefone("(87)96541-2545");
		
		mng.persist(funcionario);
		mng.getTransaction().commit();

		Funcionario funcionarioSalvar = funcionarioDaoTest.consultarPorNome("Maria");
		assertEquals("Maria", funcionarioSalvar.getNome());
	}

	@Test
	public void Test2Editar() {
		FuncionarioDaoImplTest funcionarioDaoTest = new FuncionarioDaoImplTest();
		Funcionario funcionario = funcionarioDaoTest.consultarPorNome("Maria");

		funcionario.setNome("Joao");
		funcionario.setCpf("25225154865");
		funcionario.setEmail("maria@gmail.com");
		funcionario.setFuncao("Recepcionista");
		funcionario.setNumCarteira(1556498);
		funcionario.setSalario(1250.00);
		funcionario.setTelefone("(87)96541-2545");
		mng.merge(funcionario);
		mng.getTransaction().commit();

		Funcionario funcionarioEditar = funcionarioDaoTest.consultarPorNome("Joao");
		assertEquals("Joao", funcionarioEditar.getNome());
	}

	@Test
	public void Test3Deletar() {
		FuncionarioDaoImplTest funcionarioDaoTest = new FuncionarioDaoImplTest();
		Funcionario funcionario = new Funcionario();

		funcionario.setNome("Peste");
		funcionario.setCpf("25225154865");
		funcionario.setEmail("maria@gmail.com");
		funcionario.setFuncao("Recepcionista");
		funcionario.setNumCarteira(1556498);
		funcionario.setSalario(1250.00);
		funcionario.setTelefone("(87)96541-2545");
		mng.persist(funcionario);

		mng.remove(funcionario);
		mng.getTransaction().commit();

		Funcionario funcionarioExcluir = funcionarioDaoTest.consultarPorNome("Peste");
		assertNull(funcionarioExcluir);
	}

	@Test
	public void Test4Listar() {
		FuncionarioDaoImplTest funcionarioDaoTest = new FuncionarioDaoImplTest();
		
		List<Funcionario> funcionarioLista = funcionarioDaoTest.listarTodos();
		assertEquals(1, funcionarioLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
