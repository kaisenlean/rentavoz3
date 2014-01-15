package co.innovate.rentavoz.views.bodega.consulta.ingresos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.services.bodegaingreso.BodegaIngresoService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanConsultaIngresos
 * @date 15/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanConsultaIngresos implements Serializable {

	/**
	 * 15/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = -2804220907998998248L;

	@ManagedProperty(value="#{bodegaIngresoService}")
	private BodegaIngresoService bodegaIngresoService;

	private List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();

	private Date start = new Date();
	private Date end = new Date();

	public void buscar() {

		existencias=bodegaIngresoService.findByFechas(start,end);
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the existencias
	 */
	public List<BodegaExistencia> getExistencias() {
		return existencias;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param existencias
	 *            the existencias to set
	 */
	public void setExistencias(List<BodegaExistencia> existencias) {
		this.existencias = existencias;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param bodegaIngresoService the bodegaIngresoService to set
	 */
	public void setBodegaIngresoService(
			BodegaIngresoService bodegaIngresoService) {
		this.bodegaIngresoService = bodegaIngresoService;
	}
}
