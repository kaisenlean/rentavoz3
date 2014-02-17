/**
 * 
 */
package co.innovate.rentavoz.services.venta.item.detalle;

import java.util.List;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDetalleItemService
 * @date 17/02/2014
 *
 */
public interface VentaItemDetalleItemService extends GenericService<VentaItemDetalleItem, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param ventaItem
	* @return
	*/
	List<VentaItemDetalleItem> findByVentaItem(VentaItem ventaItem);
}
