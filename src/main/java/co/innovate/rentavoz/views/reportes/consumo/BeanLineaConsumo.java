/**
 * 
 */
package co.innovate.rentavoz.views.reportes.consumo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.criterion.Order;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanLineaConsumo
 * @date 16/04/2014
 * 
 */
@ManagedBean
@ViewScoped
public class BeanLineaConsumo extends StandardAbm<LineaConsumo, Integer>
		implements Serializable {

	/**
	 * 16/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/reportes/consumo/index.jsf";

	/**
	 * 16/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{lineaConsumoService}")
	private LineaConsumoService lineaConsumoService;

	private Date fechaFiltro;
	private int corte;
	private String linea;
	private String maestra;
	private String convenio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<LineaConsumo, Integer> getFacade() {
		return lineaConsumoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public LineaConsumo getInstancia() {
		return new LineaConsumo();
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
		try {
			
			return lineaConsumoService.countByCriterio(linea, corte, maestra, convenio, fechaFiltro);
		} catch (Exception e) {
			mensajeError(e.toString());
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<LineaConsumo> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		try {

			Order order = null;
			switch (sortOrder) {

			case UNSORTED:
				order=Order.asc("id");
				break;
			case ASCENDING:
				order=Order.asc(sortField==null?"id":sortField);
				break;
			case DESCENDING:
				order=Order.asc(sortField==null?"id":sortField);
				break;
			default:
				break;
			}

			return lineaConsumoService.findByCriterio(linea, corte, maestra,
					convenio, fechaFiltro, startingAt, maxPerPage, order);
		} catch (Exception e) {
			mensajeError(e.toString());
			return new ArrayList<LineaConsumo>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public LineaConsumo getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<LineaConsumo> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
fechaFiltro=Calendar.getInstance().getTime();
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param lineaConsumoService
	 *            the lineaConsumoService to set
	 */
	public void setLineaConsumoService(LineaConsumoService lineaConsumoService) {
		this.lineaConsumoService = lineaConsumoService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @return the fechaFiltro
	 */
	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param fechaFiltro
	 *            the fechaFiltro to set
	 */
	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @return the corte
	 */
	public int getCorte() {
		return corte;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param corte
	 *            the corte to set
	 */
	public void setCorte(int corte) {
		this.corte = corte;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param linea
	 *            the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param convenio
	 *            the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @return the maestra
	 */
	public String getMaestra() {
		return maestra;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/04/2014
	 * @param maestra
	 *            the maestra to set
	 */
	public void setMaestra(String maestra) {
		this.maestra = maestra;
	}

}
