package co.innovate.rentavoz.views.components.autocomplete;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.usuario.UsuarioService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteUsuario extends Autocompletar<Usuario> {

	
	

	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract UsuarioService getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<Usuario> completarBusqueda(String query) {
		
		
		List<Usuario> lista;
			lista=
			 getFacade().findByCriterio(query);
		
		if (lista.isEmpty()) {
			mensajeError("NO hay resultados para la busqueda");
		}

		return lista;
		
	
	}

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#seleccionar(org.primefaces.event.SelectEvent)
	 */
	@Override
	public void seleccionar(SelectEvent evt) {
		if (evt.getObject()==null) {
			return ;
		}
		query = seleccionado!=null?seleccionado.toString():null;
		postSelect();
	}
	
	
	public Converter getConversorUsuario() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                if (value.trim().equals("") || value == null) {
                    return null;
                }
                Usuario c = getFacade().findById( value);
                seleccionado=c;
                return c;
            }
 
            @Override
            public String getAsString(FacesContext context, UIComponent                component, Object value) {
                if (value == null || value.equals("")) {
                    return "";
                } else {
                    return ((Usuario) value).getUsuario();
                }
            }
        };
}

	
	
	public abstract void postSelect();

}
