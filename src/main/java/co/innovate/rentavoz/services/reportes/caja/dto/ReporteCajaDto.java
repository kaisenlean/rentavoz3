/**
 * 
 */
package co.innovate.rentavoz.services.reportes.caja.dto;

import java.io.Serializable;
import java.util.List;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.venta.VentaItemCuota;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ReporteCajaDto
 * @date 27/02/2014
 *
 */
public class ReporteCajaDto implements Serializable{

	/**
	 * 27/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Sucursal sucursal;
	
	private double valorLineas;
	private double valorEquipos;
	private double total;
	
	private List<Cuota> cuotasLineas;
	private List<VentaItemCuota> cuotasEquipos;
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 */
	public ReporteCajaDto() {
	}
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/02/2014
	* @param valorLineas
	* @param valorEquipos
	*/
	public ReporteCajaDto(double valorLineas, double valorEquipos) {
		super();
		this.valorLineas = valorLineas;
		this.valorEquipos = valorEquipos;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the valorLineas
	 */
	public double getValorLineas() {
		return valorLineas;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param valorLineas the valorLineas to set
	 */
	public void setValorLineas(double valorLineas) {
		this.valorLineas = valorLineas;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the valorEquipos
	 */
	public double getValorEquipos() {
		return valorEquipos;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param valorEquipos the valorEquipos to set
	 */
	public void setValorEquipos(double valorEquipos) {
		this.valorEquipos = valorEquipos;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the cuotasLineas
	 */
	public List<Cuota> getCuotasLineas() {
		return cuotasLineas;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param cuotasLineas the cuotasLineas to set
	 */
	public void setCuotasLineas(List<Cuota> cuotasLineas) {
		this.cuotasLineas = cuotasLineas;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the cuotasEquipos
	 */
	public List<VentaItemCuota> getCuotasEquipos() {
		return cuotasEquipos;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param cuotasEquipos the cuotasEquipos to set
	 */
	public void setCuotasEquipos(List<VentaItemCuota> cuotasEquipos) {
		this.cuotasEquipos = cuotasEquipos;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}  /**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
}
