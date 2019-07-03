package controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.Entidades.Cardapio;


public class CardapioConverter implements Converter{

	/*
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 EntityManager em = JPAManager.getInstance().getEntityManager();
	        return em.find(Cardapio.class, Long.valueOf(value));
	}

	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		 Cardapio cardapio = (Cardapio) obj;  
         return String.valueOf(cardapio.getCodCardapio());
	}*/
	
	/*
	
	    public Object getAsObject(FacesContext context, UIComponent component,
	            String value) {                

	        if(value == null || value.isEmpty()){
	            return null;
	        }else{
	            Long id = Long.parseLong(value);
	            Cardapio unidade = new Cardapio<Cardapio>(Cardapio.class).find(id);
	            System.out.println(unidade.getCategoria());
	            return unidade;
	        }

	    }

	    public String getAsString(FacesContext context, UIComponent component,
	            Object value) {
	    	Cardapio unidade = (Cardapio)value;
	        if(unidade != null){
	            return String.valueOf(unidade.getCodCardapio());
	        }else{
	            return null;
	        }

	    }*/
	
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Cardapio) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Cardapio) {
        	Cardapio entity= (Cardapio) value;
            if (entity != null && entity instanceof Cardapio && entity.getCodCardapio() != null) {
                uiComponent.getAttributes().put( entity.getCodCardapio().toString(), entity);
                return entity.getCodCardapio().toString();
            }
        }
        return "";
    }


}
