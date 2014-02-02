package co.innovate.rentavoz.views.components.autocomplete;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.services.ciudad.CiudadService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteCiudad extends Autocompletar<Ciudad> {

	
	

	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract CiudadService getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<Ciudad> completarBusqueda(String query) {
		
		
		List<Ciudad> lista;
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
	
	
	public Converter getConversorCiudad() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                if (value.trim().equals("") || value == null) {
                    return null;
                }
                int number = Integer.parseInt(value);
                Ciudad c = getFacade().findById( number);
                seleccionado=c;
                return c;
            }
 
            @Override
            public String getAsString(FacesContext context, UIComponent                component, Object value) {
                if (value == null || value.equals("")) {
                    return "";
                } else {
                    return ((Ciudad) value).getIdCiudad()+"";
                }
            }
        };
}

	
	
	public abstract void postSelect();


}
