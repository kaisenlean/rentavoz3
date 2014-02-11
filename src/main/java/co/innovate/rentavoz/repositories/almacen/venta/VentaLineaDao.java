/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta;

import java.util.List;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaService
 * @date 5/02/2014
 *
 */
public interface VentaLineaDao extends GenericRepository<VentaLinea, Integer>{

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	List<VentaLinea> findByVenta(Venta venta);
}
