package controller;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FiltroConexao", urlPatterns = ("/*"))
public class SecurityFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest requisicao = (HttpServletRequest) req;
		HttpServletResponse resposta = (HttpServletResponse) resp;
		HttpSession sessao = requisicao.getSession(false);

		String loginURI = requisicao.getContextPath() + "/Login.xhtml";

		boolean logado = sessao != null && sessao.getAttribute("user") != null;
		boolean paginaLogin = requisicao.getRequestURI().equals(loginURI);
		boolean requisicaoRecurso = requisicao.getRequestURI()
				.startsWith(requisicao.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		if (logado || paginaLogin || requisicaoRecurso || requisicao.getRequestURI().endsWith("Inicio.xhtml")
				|| requisicao.getRequestURI().endsWith("Cardapio.xhtml")
				|| requisicao.getRequestURI().endsWith("CadastroCliente.xhtml")
				|| requisicao.getRequestURI().endsWith("Sobre.xhtml")
				|| requisicao.getRequestURI().contains("javax.faces.resource/")) {
			chain.doFilter(requisicao, resposta);
		} else {
			resposta.sendRedirect(loginURI);
		}
	}
}
