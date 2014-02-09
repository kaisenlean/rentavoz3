/**
 * 
 */
package co.innovate.rentavoz.logic.venta.linea;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.venta.Venta;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaService
 * @date 4/02/2014
 *
 */
public interface VentaControllerService {

	
	/**
	 * Metodo que guarda la transaccion de una venta de lineas
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/02/2014
	* @throws BaseException
	*/
	Venta guardarVentaLinea(Venta venta)throws BaseException;
}
