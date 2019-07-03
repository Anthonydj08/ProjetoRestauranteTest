package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.util.FacesUtil;
import model.CardapioModel;
import model.ItemCardapioModel;
import model.Entidades.Cardapio;
import model.Entidades.ItemCardapio;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;

@ManagedBean(name = "cardapioController")
@RequestScoped
public class CardapioController {
	private Cardapio cardapio;
	private ItemCardapio itemCardapio = new ItemCardapio();
	private List<Cardapio> listaCardapio;
	private List<ItemCardapio> listaItemCardapio;
	
	private CardapioModel cm = new CardapioModel();
	private ItemCardapioModel icm = new ItemCardapioModel();
	
	public Cardapio getCardapio() {
		return this.cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public List<Cardapio> getListaCardapio() {
		listaCardapio = cm.listarTodos();
		return listaCardapio;
	}

	public void setListaCardapio(List<Cardapio> listaCardapio) {
		this.listaCardapio = listaCardapio;
	}

	public CardapioController() {
		this.cardapio = new Cardapio();
	}

	public ItemCardapio getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(ItemCardapio itemCardapio) {
		this.itemCardapio = itemCardapio;
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
			cm.registraCategoria(this.cardapio);
			FacesUtil.adicionarMsgInfo("Cardapio salvo com Sucesso.");
		} catch (JaExisteException ex) {
			FacesUtil.adicionarMsgErro(ex.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		}
	}

	public void excluir(Cardapio c) {
		cm.removeCategoria(c);
		FacesUtil.adicionarMsgInfo("Cardapio excluido.");
	}

	public String editar() {
		try {
			cm.atualizarCategoria(this.cardapio);
			FacesUtil.adicionarMsgInfo("Cardapio atualizado com Sucesso.");
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (JaExisteException je) {
			FacesUtil.adicionarMsgErro(je.getMessage());
		}
		return "";
	}
}
