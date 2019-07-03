package controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import controller.util.FacesUtil;
import model.ContaModel;
import model.Entidades.Conta;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.ValorException;

@ApplicationScoped
@ManagedBean(name = "contaController")
public class ContaController {
	private Conta conta;
	private List<Conta> listaConta;
	private ContaModel cm = ContaModel.getInstance();

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getListaConta() {
		listaConta = cm.listarTodos();
		return listaConta;
	}

	public void setListaConta(List<Conta> listaConta) {
		this.listaConta = listaConta;
	}

	public void salvar(Conta c) {
		try {
			cm.registraConta(c);
			FacesUtil.adicionarMsgInfo("Cliente Salvo com Sucesso.");
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

	public void excluir(Conta c) {
		cm.removeConta(c);
		FacesUtil.adicionarMsgInfo("Conta excluida.");
	}

	public void editar() {
		try {
			cm.atualizaConta(this.conta);
			FacesUtil.adicionarMsgInfo("Conta alterada.");
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		}
	}
}
