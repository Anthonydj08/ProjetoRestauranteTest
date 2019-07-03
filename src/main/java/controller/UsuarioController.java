package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.util.FacesUtil;
import model.UsuarioModel;
import model.Entidades.Usuario;
import model.Exception.StringException;

@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController {
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private UsuarioModel um = new UsuarioModel();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = um.listarTodos();
	}

	public UsuarioController() {
		this.usuario = new Usuario();
	}

	public void salvar(Usuario usuario){
		try {
			um.registraUsuario(usuario);
			FacesUtil.adicionarMsgInfo("usuario salvo com Sucesso.");
		}catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		}
	}

	public void salvar2(Usuario usuario22) {
		try {
			um.registraUsuario(usuario22);
			FacesUtil.adicionarMsgInfo("usuario salvo com Sucesso.");
		}catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		}

	}

	public void excluir(Usuario u) {
		um.removeCliente(u);
		FacesUtil.adicionarMsgInfo("usuario excluido.");
		
	}

	public String editar(Usuario usuario22){
		try {
			um.atualizaUsuario(usuario22);
			FacesUtil.adicionarMsgInfo("Cardapio atualizado com Sucesso.");
		} catch (Exception se) {
			FacesUtil.adicionarMsgErro("Erro ao editar");
		}
			return "";
	}

}
