/**
 * 
 */
package co.innovate.rentavoz.views.venta.linea.devolucion;

import java.io.Serializable;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class DevolucionDto
 * @date 11/03/2014
 *
 */
public class DevolucionDto implements Serializable {

	/**
	 * 11/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double valorDevolucion;
	
	private Tercero cliente;
	
	private Tercero vendedor;
	
	
	private VentaLinea ventaLinea;


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 */
	public DevolucionDto() {
	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 11/03/2014
	* @param valorDevolucion
	* @param cliente
	*/
	public DevolucionDto(double valorDevolucion, Tercero cliente) {
		super();
		this.valorDevolucion = valorDevolucion;
		this.cliente = cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @return the valorDevolucion
	 */
	public double getValorDevolucion() {
		return valorDevolucion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param valorDevolucion the valorDevolucion to set
	 */
	public void setValorDevolucion(double valorDevolucion) {
		this.valorDevolucion = valorDevolucion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @return the cliente
	 */
	public Tercero getCliente() {
		return cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param cliente the cliente to set
	 */
	public void setCliente(Tercero cliente) {
		this.cliente = cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @return the vendedor
	 */
	public Tercero getVendedor() {
		return vendedor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Tercero vendedor) {
		this.vendedor = vendedor;
	}
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 11/03/2014
 * @return the ventaLinea
 */
public VentaLinea getVentaLinea() {
	return ventaLinea;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 11/03/2014
 * @param ventaLinea the ventaLinea to set
 */
public void setVentaLinea(VentaLinea ventaLinea) {
	this.ventaLinea = ventaLinea;
}
	
	
}
