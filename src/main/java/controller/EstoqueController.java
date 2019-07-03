package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.util.FacesUtil;
import model.EstoqueModel;
import model.Entidades.Estoque;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.ValorException;

@ManagedBean(name = "estoqueController")
@RequestScoped
public class EstoqueController {
	private Estoque estoque;
	private List<Estoque> listaEstoque;
	private EstoqueModel em = new EstoqueModel();

	public Estoque getEstoque() {
		return this.estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public EstoqueController() {
		this.estoque = new Estoque();
	}

	public List<Estoque> getListaEstoque() {
		listaEstoque = em.listarTodos();
		return listaEstoque;
	}

	public void setListaEstoque(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public void salvar() {
		try {
			em.registraEstoque(this.estoque);
			FacesUtil.adicionarMsgInfo("Estoque Salvo com Sucesso.");
		} catch (JaExisteException ee) {
			FacesUtil.adicionarMsgErro(ee.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}
	}

	public void excluir(Estoque e) {
		em.removeEstoque(e);
		FacesUtil.adicionarMsgInfo("Estoque excluido.");
	}

	public String editar() {
		try {
			em.atualizaEstoque(this.estoque);
			FacesUtil.adicionarMsgInfo("Estoque alterada.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (JaExisteException jee) {
			FacesUtil.adicionarMsgErro(jee.getMessage());
		}catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}
		return "";
	}

}
