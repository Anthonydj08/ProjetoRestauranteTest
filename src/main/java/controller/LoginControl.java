package controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.UsuarioModel;
import model.Entidades.Usuario;

@ManagedBean(name = "loginControl")
@SessionScoped
public class LoginControl {
	private String usuario;
	private String senha;
	private Usuario usuariologin;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LoginControl() {
		usuariologin = new Usuario();
	}

	public void logar() {
		FacesContext con = FacesContext.getCurrentInstance();

		if (this.usuario.equals(usuario) && this.senha.equals(senha)
				|| this.usuario.equals("admin") && this.senha.equals("admin")) {
			UsuarioModel cm = new UsuarioModel();
			usuariologin = cm.autenticar(usuario, senha);
			con.getExternalContext().getSessionMap().put("user", usuario);
			try {
				if(usuariologin.getTipo() == 1) {
					con.getExternalContext().redirect("cliente/Mesa.xhtml");
				} else {
					con.getExternalContext().redirect("funcionario/PesquisaCardapio.xhtml");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// con.addMessage(null, new FacesMessage("falha ao entrar."));
		
		}
	}
	
	public Integer tipologado() {
		return usuariologin.getTipo();
	}
	
	private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void deslogar() {
		FacesContext con = FacesContext.getCurrentInstance();
		con.getExternalContext().invalidateSession();
		try {
			con.getExternalContext().redirect("/RestauranteProjeto/Inicio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
