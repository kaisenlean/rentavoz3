/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaService
 * @date 5/02/2014
 *
 */
public interface VentaService extends GenericService<Venta, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/05/2014
	* @param consecutivo
	* @return
	*/
	Venta findByConsecutivo(String consecutivo);
}
