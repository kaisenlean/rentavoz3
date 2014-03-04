/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea;

import java.util.List;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaService
 * @date 5/02/2014
 *
 */
public interface VentaLineaService extends GenericService<VentaLinea, Integer> {

	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	List<VentaLinea> findByVenta(Venta venta);
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	* @param linea
	* @return
	*/
	List<VentaLinea> findHistorialFacturacion(Linea linea);
}
