/**
 * 
 */
package co.innovate.rentavoz.model.ajustefactura;

import java.io.Serializable;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.venta.VentaItem;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class AjusteFacturaDto
 * @date 23/04/2014
 *
 */
public class AjusteFacturaDto implements Serializable{

	/**
	 * 23/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;



	private boolean facturaLinea;
	
	
	
	private Venta venta;
	
	
	private VentaItem ventaItem;
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 */
	public AjusteFacturaDto() {

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @return the facturaLinea
	 */
	public boolean isFacturaLinea() {
		return facturaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @param facturaLinea the facturaLinea to set
	 */
	public void setFacturaLinea(boolean facturaLinea) {
		this.facturaLinea = facturaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @param venta the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @return the ventaItem
	 */
	public VentaItem getVentaItem() {
		return ventaItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/04/2014
	 * @param ventaItem the ventaItem to set
	 */
	public void setVentaItem(VentaItem ventaItem) {
		this.ventaItem = ventaItem;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AjusteFacturaDto [facturaLinea=" + facturaLinea + ", venta="
				+ venta + ", ventaItem=" + ventaItem + "]";
	}
	
	
	
	
}
