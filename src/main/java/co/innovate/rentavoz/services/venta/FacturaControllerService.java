/**
 * 
 */
package co.innovate.rentavoz.services.venta;

import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FacturaControllerService
 * @date 10/02/2014
 *
 */
public interface FacturaControllerService {

	/**
	* Método que carga las cuotas y detalle de una factura de venta de lineas 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	Venta cargarFactura(Venta venta);

	
	/**
	* Método que actualiza una factura en base a las cuotas pagadas
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	Venta pagarCuota(Cuota cuotas);
	
	
	
	/**
	 *  Método que carga las cuotas y detalle de una factura de venta de items 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param venta
	* @return
	*/
	VentaItem cargarFacturaItem(VentaItem venta);
	
	/**
	* Método que actualiza una factura en base a las cuotas pagadas
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	VentaItem pagarCuotaItem(VentaItemCuota cuotas);
	
}
