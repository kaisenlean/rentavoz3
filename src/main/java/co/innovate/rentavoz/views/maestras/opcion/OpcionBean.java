/**
 * 
 */
package co.innovate.rentavoz.views.maestras.opcion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionBean
 * @date 18/01/2014
 *
 */
@ManagedBean
@ViewScoped
public class OpcionBean extends StandardAbm<Opcion	, String> implements Serializable{

	
	/**
	 * 18/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/maestras/opcion/index.jsf";
	@ManagedProperty("#{opcionService}")
	private OpcionService opcionService;
	/**
	 * 18/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Opcion, String> getFacade() {
		return opcionService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public Opcion getInstancia() {
		return new Opcion();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public Opcion getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<Opcion> getListado() {
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
	 * @date 18/01/2014
	 * @param opcionService the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}

}
