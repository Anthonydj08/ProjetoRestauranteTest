package controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import controller.util.FacesUtil;
import model.FuncionarioModel;
import model.Entidades.Funcionario;
import model.Entidades.Usuario;
import model.Exception.CpfException;
import model.Exception.EmailException;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.TelefoneException;
import model.Exception.ValorException;

@ManagedBean(name = "funcionarioController")
@ApplicationScoped
public class FuncionarioController {
	private Funcionario funcionario;
	private Usuario usuario;
	private List<Funcionario> listaFuncionario;
	FuncionarioModel fm = new FuncionarioModel();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioController() {
		this.usuario = new Usuario();
		this.funcionario = new Funcionario();
	}

	public List<Funcionario> getListaFuncionario() {
		listaFuncionario = fm.listarTodos();
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public void salvar() {
		UsuarioController uc = new UsuarioController();
		try {
			usuario.setTipo(2);
			uc.salvar(this.usuario);
			fm.registraFuncionario(this.funcionario);
		} catch (JaExisteException ee) {
			FacesUtil.adicionarMsgErro(ee.getMessage());
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (EmailException eme) {
			FacesUtil.adicionarMsgErro(eme.getMessage());
		} catch (CpfException ce) {
			FacesUtil.adicionarMsgErro(ce.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		} catch (TelefoneException te) {
			FacesUtil.adicionarMsgErro(te.getMessage());
		}
	}

	public void excluir(Funcionario f) {
		fm.removeFuncionario(f);
		;
		FacesUtil.adicionarMsgInfo("Funcionário excluido.");
	}

	public String editar(Funcionario f) {
		try {
			usuario.setTipo(2);
			fm.atualizaFuncionario(f);
			;
			FacesUtil.adicionarMsgInfo("Funcionário alterado.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (EmailException eme) {
			FacesUtil.adicionarMsgErro(eme.getMessage());
		} catch (CpfException ce) {
			FacesUtil.adicionarMsgErro(ce.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		} catch (TelefoneException te) {
			FacesUtil.adicionarMsgErro(te.getMessage());
		}

		return "";
	}
}
