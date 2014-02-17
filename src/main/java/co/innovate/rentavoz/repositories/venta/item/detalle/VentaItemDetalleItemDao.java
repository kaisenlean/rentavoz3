/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.detalle;

import java.util.List;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDetalleItemDao
 * @date 17/02/2014
 *
 */
public interface VentaItemDetalleItemDao extends GenericRepository<VentaItemDetalleItem, Integer>{
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param ventaItem
	* @return
	*/
	List<VentaItemDetalleItem> findByVentaItem(VentaItem ventaItem);
	
}
