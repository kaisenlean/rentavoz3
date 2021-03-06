/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaService
 * @date 4/02/2014
 *
 */
public interface VentaDao extends GenericRepository<Venta, Integer>  {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/05/2014
	* @param consecutivo
	* @return
	*/
	Venta findByConsecutivo(String consecutivo);
}
