/**
 * 
 */
package co.innovate.rentavoz.views.maestras.fechafacturacion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.fecha.UtilidadFechaService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacionBean
 * @date 26/01/2014
 *
 */
@ManagedBean
@ViewScoped
public class FechaFacturacionBean extends StandardAbm<FechaFacturacion, Integer> implements Serializable {

	@ManagedProperty(value="#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService;
	
	@ManagedProperty(value="#{utilidadFechaService}")
	private UtilidadFechaService utilidadFechaService;
	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<FechaFacturacion, Integer> getFacade() {
		return fechaFacturacionService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public FechaFacturacion getInstancia() {
		return new FechaFacturacion();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/fechafacturacion/index.jsf";
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
	public List<FechaFacturacion> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public FechaFacturacion getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<FechaFacturacion> getListado() {
		return obtenerListado();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		
	}
	
	public void extraerMesesByFechas(SelectEvent selectEvent){
		
		try {
			
		getObjeto().setLabel(utilidadFechaService.obtenerMesesPorRangoDeFechas(getObjeto().getFechaInicio(), getObjeto().getFechaFin()));
		
		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param fechaFacturacionService the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param utilidadFechaService the utilidadFechaService to set
	 */
	public void setUtilidadFechaService(
			UtilidadFechaService utilidadFechaService) {
		this.utilidadFechaService = utilidadFechaService;
	}

}
