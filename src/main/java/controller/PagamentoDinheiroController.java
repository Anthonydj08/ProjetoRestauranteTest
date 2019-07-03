package controller;

import controller.util.FacesUtil;
import model.PagamentoDinheiroModel;
import model.Entidades.PagamentoDinheiro;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.ValorException;

public class PagamentoDinheiroController {
	private PagamentoDinheiro pagamentoDinheiro;
	PagamentoDinheiroModel pdm = new PagamentoDinheiroModel();
	
	public PagamentoDinheiro getPagamentoDinheiro() {
		return pagamentoDinheiro;
	}
	public void setPagamentoDinheiro(PagamentoDinheiro pagamentoDinheiro) {
		this.pagamentoDinheiro = pagamentoDinheiro;
	}
	
	public void salvar(PagamentoDinheiro pd) {
		try {
			pdm.registraPagamentoDinheiro(pd);
			FacesUtil.adicionarMsgInfo("Pagamento dinheiro salvo com Sucesso.");
		} catch (JaExisteException ee) {
			FacesUtil.adicionarMsgErro(ee.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}
	}

	public void excluir(PagamentoDinheiro pd) {
		pdm.deletaPagamentoDinheiro(pd);
		FacesUtil.adicionarMsgInfo("Pagamento dinheiro excluido.");
	}

	public String editar(PagamentoDinheiro pd) {
		try {
			pdm.atualizaPagamentoDinheiro(pd);
			FacesUtil.adicionarMsgInfo("Pagamento dinheiros alterado.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		}
		return "";
	}		
	
}
