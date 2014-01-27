/**
 * 
 */
package co.innovate.rentavoz.views.maestras.centrope;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanCentrope
 * @date 27/01/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanCentrope extends StandardAbm<Centrope, Integer> implements Serializable
{
	
	@ManagedProperty(value="#{centropeService}")
	private CentropeService centropeService;

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Centrope, Integer> getFacade() {
		return centropeService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public Centrope getInstancia() {
		return new Centrope();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/centrope/index.jsf";
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<Centrope> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public Centrope getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<Centrope> getListado() {
		return obtenerListado();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param centropeService the centropeService to set
	 */
	public void setCentropeService(CentropeService centropeService) {
		this.centropeService = centropeService;
	}
}
