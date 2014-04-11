/**
 * 
 */
package co.innovate.rentavoz.views.maestras.planpago;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.planpago.PlanPagoService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanPagoBean
 * @date 10/04/2014
 *
 */
@ManagedBean
@ViewScoped
public class PlanPagoBean extends StandardAbm<PlanPago, Integer> implements Serializable {

	/**
	 * 10/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * URL_NAVEGACION
	 */
	private static final String URL_NAVEGACION = "/paginas/maestras/planpago/index.jsf";


	/**
	 * 10/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{planPagoService}")
	private PlanPagoService planPagoService;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<PlanPago, Integer> getFacade() {
		return planPagoService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public PlanPago getInstancia() {
		return new PlanPago();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return URL_NAVEGACION;
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
	public List<PlanPago> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public PlanPago getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<PlanPago> getListado() {
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
 * @date 10/04/2014
 * @param planPagoService the planPagoService to set
 */
public void setPlanPagoService(PlanPagoService planPagoService) {
	this.planPagoService = planPagoService;
}
}
