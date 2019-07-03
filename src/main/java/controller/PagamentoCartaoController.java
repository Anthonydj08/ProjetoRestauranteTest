package controller;

import controller.util.FacesUtil;
import model.PagamentoCartaoModel;
import model.Entidades.PagamentoCartao;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;

public class PagamentoCartaoController {
	private PagamentoCartao pagamentocartao;
	PagamentoCartaoModel pcm = new PagamentoCartaoModel();
	
	public PagamentoCartao getPagamentocartao() {
		return pagamentocartao;
	}
	public void setPagamentocartao(PagamentoCartao pagamentocartao) {
		this.pagamentocartao = pagamentocartao;
	}
	
	public void salvar(PagamentoCartao pc) {
		try {
			pcm.registraPagamentoCartao(pc);
			FacesUtil.adicionarMsgInfo("Pagamento cartão salvo com Sucesso.");
		} catch (JaExisteException ee) {
			FacesUtil.adicionarMsgErro(ee.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		}
	}

	public void excluir(PagamentoCartao pc) {
		pcm.deletaPagamentoCartao(pc);
		FacesUtil.adicionarMsgInfo("Pagamento cartão excluido.");
	}

	public String editar(PagamentoCartao pc) {
		try {
			pcm.atualizaPagamentoCartao(pc);
			FacesUtil.adicionarMsgInfo("Pagamento cartão alterado.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		}
		return "";
	}		
	
	
}
