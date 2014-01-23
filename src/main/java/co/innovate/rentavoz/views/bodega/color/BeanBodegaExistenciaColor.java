/**
 * 
 */
package co.innovate.rentavoz.views.bodega.color;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.bodega.BodegaExistenciaColor;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.bodegaexistencia.color.BodegaExistenciaColorService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanBodegaExistenciaColor
 * @date 19/01/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanBodegaExistenciaColor extends StandardAbm<BodegaExistenciaColor, Integer> implements Serializable {

	/**
	 * 19/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/color/index.jsf";


	/**
	 * 19/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@ManagedProperty(value="#{bodegaExistenciaColorService}")
	private BodegaExistenciaColorService bodegaExistenciaColorService;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<BodegaExistenciaColor, Integer> getFacade() {
		return bodegaExistenciaColorService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaExistenciaColor getInstancia() {
		return new BodegaExistenciaColor();
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
	public BodegaExistenciaColor getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaExistenciaColor> getListado() {
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
	 * @date 19/01/2014
	 * @param bodegaExistenciaColorService the bodegaExistenciaColorService to set
	 */
	public void setBodegaExistenciaColorService(
			BodegaExistenciaColorService bodegaExistenciaColorService) {
		this.bodegaExistenciaColorService = bodegaExistenciaColorService;
	}

}
