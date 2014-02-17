/**
 * 
 */
package co.innovate.rentavoz.services.venta.item.cuota;

import java.util.List;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemCuotaService
 * @date 15/01/2014
 *
 */
public interface VentaItemCuotaService extends GenericService<VentaItemCuota, Integer> {

	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/02/2014
	* @param tercero
	* @return
	*/
	List<VentaItemCuota> findCuotasPendientesByCliente(Tercero tercero);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param ventaItem
	* @return
	*/
	List<VentaItemCuota> findCuotasByVenta(VentaItem ventaItem);
	
	
	
}
