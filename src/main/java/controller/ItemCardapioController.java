package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.util.FacesUtil;
import model.ItemCardapioModel;
import model.Entidades.Estoque;
import model.Entidades.ItemCardapio;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.ValorException;

@ManagedBean(name = "itemCardapioController")
@RequestScoped
public class ItemCardapioController {
	private ItemCardapio itemCardapio;
	private List<ItemCardapio> listaItemCardapio;
	ItemCardapioModel icm = new ItemCardapioModel();
	private Estoque estoque;
	
	public ItemCardapio getItemCardapio() {
		return this.itemCardapio;
	}

	public void setItemCardapio(ItemCardapio itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	public ItemCardapioController() {
		this.itemCardapio = new ItemCardapio();
		this.estoque = new Estoque();

	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<ItemCardapio> getListaItemCardapio() {
		listaItemCardapio = icm.listarTodos();
		return listaItemCardapio;
	}

	public void setListaItemCardapio(List<ItemCardapio> listaItemCardapio) {
		this.listaItemCardapio = listaItemCardapio;
	}

	public void salvar() {
		try {
			icm.registraItemCardapio(this.itemCardapio);
			FacesUtil.adicionarMsgInfo("Item cardápio Salvo com Sucesso.");
		} catch (JaExisteException ee) {
			FacesUtil.adicionarMsgErro(ee.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}
	}

	public void excluir(ItemCardapio ic) {
		icm.removeItemCardapio(ic);
		FacesUtil.adicionarMsgInfo("Item cardápio excluido.");
	}

	public String editar() {
		try {
			icm.atualizaItemCardapio(this.itemCardapio);
			FacesUtil.adicionarMsgInfo("Item cardápio alterado.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}

		return "";
	}
}
