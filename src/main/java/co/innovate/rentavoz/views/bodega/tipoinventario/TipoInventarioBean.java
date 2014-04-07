/**
 * 
 */
package co.innovate.rentavoz.views.bodega.tipoinventario;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.bodega.TipoInventarioService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventarioBean
 * @date 7/04/2014
 * 
 */
@ManagedBean
@ViewScoped
public class TipoInventarioBean extends StandardAbm<TipoInventario, Integer>
		implements Serializable {

	/**
	 * 7/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/tipoinventario/index.jsf";
	/**
	 * 7/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{tipoInventarioService}")
	private TipoInventarioService tipoInventarioService;

	private String selClave;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {

		try {
			if (!selClave.equals(StringUtils.EMPTY)) {

				getObjeto().setClave(selClave);
				return true;
			}

		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		selClave = getObjeto().getClave();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<TipoInventario, Integer> getFacade() {
		return tipoInventarioService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public TipoInventario getInstancia() {
		return new TipoInventario();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String
	 * )
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<TipoInventario> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public TipoInventario getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<TipoInventario> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @param tipoInventarioService
	 *            the tipoInventarioService to set
	 */
	public void setTipoInventarioService(
			TipoInventarioService tipoInventarioService) {
		this.tipoInventarioService = tipoInventarioService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @return the selClave
	 */
	public String getSelClave() {
		return selClave;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @param selClave
	 *            the selClave to set
	 */
	public void setSelClave(String selClave) {
		this.selClave = selClave;
	}
}
