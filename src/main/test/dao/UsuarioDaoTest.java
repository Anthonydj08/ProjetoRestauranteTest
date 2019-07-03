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

import model.Entidades.Usuario;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();

	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Usuario usuario = new Usuario();
		UsuarioDaoImplTest usuarioDaoTest = new UsuarioDaoImplTest();

		usuario.setLogin("Gerik@gmail.com");
		usuario.setSenha("teste123");
		usuario.setTipo(1);
		mng.persist(usuario);
		mng.getTransaction().commit();

		Usuario usuarioSalvar = usuarioDaoTest.consultarPorNome("Gerik@gmail.com");
		assertEquals("Gerik@gmail.com", usuarioSalvar.getLogin());
	}

	@Test
	public void Test2Editar() {
		UsuarioDaoImplTest usuarioDaoTest = new UsuarioDaoImplTest();
		Usuario usuario = usuarioDaoTest.consultarPorNome("Gerik@gmail.com");

		usuario.setLogin("matuza@gmail.com");
		usuario.setSenha("teste123");
		usuario.setTipo(1);
		mng.merge(usuario);
		mng.getTransaction().commit();

		Usuario usuarioEditar = usuarioDaoTest.consultarPorNome("matuza@gmail.com");
		assertEquals("matuza@gmail.com", usuarioEditar.getLogin());
	}

	@Test
	public void Test3Deletar() {
		UsuarioDaoImplTest usuarioDaoTest = new UsuarioDaoImplTest();
		Usuario usuario = new Usuario();

		usuario.setLogin("Ratathy@gmail.com");
		usuario.setSenha("teste123");
		usuario.setTipo(1);
		mng.persist(usuario);

		mng.remove(usuario);
		mng.getTransaction().commit();

		Usuario usuarioExcluir = usuarioDaoTest.consultarPorNome("Ratathy@gmail.com");
		assertNull(usuarioExcluir);
	}

	@Test
	public void Test4Listar() {
		UsuarioDaoImplTest usuarioDaoTest = new UsuarioDaoImplTest();
		
		List<Usuario> usuarioLista = usuarioDaoTest.listarTodos();
		assertEquals(1, usuarioLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
