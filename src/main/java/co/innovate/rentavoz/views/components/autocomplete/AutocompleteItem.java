package co.innovate.rentavoz.views.components.autocomplete;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.services.bodegaitem.BodegaItemService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteItem extends Autocompletar<BodegaItem> {

	
	

	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract BodegaItemService getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<BodegaItem> completarBusqueda(String query) {
		
		
		List<BodegaItem> lista;
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
	
	
	public Converter getConversorClientes() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                if (value.trim().equals("") || value == null) {
                    return null;
                }
                int number = Integer.parseInt(value);
                BodegaItem c = getFacade().findById( number);
                seleccionado=c;
                return c;
            }
 
            @Override
            public String getAsString(FacesContext context, UIComponent                component, Object value) {
                if (value == null || value.equals("")) {
                    return "";
                } else {
                    return ((BodegaItem) value).getId()+"";
                }
            }
        };
}

	
	
	public abstract void postSelect();

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param valor
	 * @return
	 */
	@SuppressWarnings("unused")
	private String obtenerId(String valor) {

		String id = "";
		Pattern p = Pattern.compile("\\[d+");
		Matcher m = p.matcher(valor);
		while (m.find()) {
			id += m.group();
		}

		return id;
	}
}
