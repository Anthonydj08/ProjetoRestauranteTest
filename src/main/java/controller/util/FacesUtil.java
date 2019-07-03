package controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	public static void adicionarMsgInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		
		FacesContext facescontext = FacesContext.getCurrentInstance();
		facescontext.addMessage(null, facesMessage);
	}
	
    public static void adicionarMsgErro(String mensagem) {
    	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
	
    	FacesContext facescontext = FacesContext.getCurrentInstance();
		facescontext.addMessage(null, facesMessage);
    }	
}
