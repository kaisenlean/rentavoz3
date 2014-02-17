/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.cuota;

import java.util.List;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemCuotaDao
 * @date 15/01/2014
 *
 */
public interface VentaItemCuotaDao extends GenericRepository<VentaItemCuota, Integer> {

	
	
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
