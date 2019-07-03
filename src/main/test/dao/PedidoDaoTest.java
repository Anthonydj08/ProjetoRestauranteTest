package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Entidades.Cliente;
import model.Entidades.Conta;
import model.Entidades.Entrega;
import model.Entidades.Pedido;
import model.dao.util.JPAManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PedidoDaoTest {

	EntityManager mng = JPAManager.getInstance().getEntityManager();
	Date data = new Date();
	@Before
	public void antes() {
		mng.getTransaction().begin();
	}

	@Test
	public void Test1Salvar() {
		Pedido pedido = new Pedido();
		PedidoDaoImplTest pedidoDaoTest = new PedidoDaoImplTest();

		Cliente cliente = new Cliente();
		cliente.setNome("Peste");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		mng.persist(cliente);

		Conta conta = new Conta();
		conta.setStatus("Aberto");
		conta.setValorTotal(200);
		mng.persist(conta);
		
		Entrega entrega = new Entrega();
		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua 35");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.persist(entrega);
		
		pedido.setStatus("Em preparo");
		pedido.setData(data);
		pedido.setMesa(5);
		pedido.setCliente(cliente);
		pedido.setConta(conta);
		pedido.setEntrega(entrega);
		
		mng.persist(pedido);
		mng.getTransaction().commit();

		Pedido pedidoSalvar = pedidoDaoTest.consultarPorNome("Em preparo");
		assertEquals("Em preparo", pedidoSalvar.getStatus());
	}

	@Test
	public void Test2Editar() {
		PedidoDaoImplTest pedidoDaoTest = new PedidoDaoImplTest();
		Pedido pedido = pedidoDaoTest.consultarPorNome("Em preparo");

		Cliente cliente = new Cliente();
		cliente.setNome("Peste");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		mng.persist(cliente);

		Conta conta = new Conta();
		conta.setStatus("Aberto");
		conta.setValorTotal(200);
		mng.persist(conta);
		
		Entrega entrega = new Entrega();
		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua 35");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.persist(entrega);
		
		pedido.setStatus("Entregue");
		pedido.setData(data);
		pedido.setMesa(5);
		pedido.setCliente(cliente);
		pedido.setConta(conta);
		pedido.setEntrega(entrega);
		
		mng.persist(pedido);
		mng.getTransaction().commit();

		Pedido pedidoEditar = pedidoDaoTest.consultarPorNome("Entregue");
		assertEquals("Entregue", pedidoEditar.getStatus());
	}

	@Test
	public void Test3Deletar() {
		PedidoDaoImplTest pedidoDaoTest = new PedidoDaoImplTest();
		Pedido pedido = new Pedido();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Peste");
		cliente.setCpf("25225154865");
		cliente.setEmail("maria@gmail.com");
		cliente.setTelefone("(87)96541-2545");
		mng.persist(cliente);

		Conta conta = new Conta();
		conta.setStatus("Aberto");
		conta.setValorTotal(200);
		mng.persist(conta);
		
		Entrega entrega = new Entrega();
		entrega.setBairro("Centro");
		entrega.setComplemento("Proximo a vila");
		entrega.setNumero(125);
		entrega.setRua("Rua 35");
		entrega.setStatus("Entregue");
		entrega.setCep(55525105);
		mng.persist(entrega);
		
		pedido.setStatus("Em preparo");
		pedido.setData(data);
		pedido.setMesa(5);
		pedido.setCliente(cliente);
		pedido.setConta(conta);
		pedido.setEntrega(entrega);
		
		mng.persist(pedido);

		mng.remove(pedido);
		mng.getTransaction().commit();

		Pedido pedidoExcluir = pedidoDaoTest.consultarPorNome("Cancelado");
		assertNull(pedidoExcluir);
	}

	@Test
	public void Test4Listar() {
		PedidoDaoImplTest pedidoDaoTest = new PedidoDaoImplTest();
		
		List<Pedido> pedidoLista = pedidoDaoTest.listarTodos();
		assertEquals(1, pedidoLista.size());
	}

	@After
	public void depois() {
		//mng.getTransaction().rollback();
		mng.close();
	}

}
