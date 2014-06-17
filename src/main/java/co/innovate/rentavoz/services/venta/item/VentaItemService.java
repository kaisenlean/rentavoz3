/**
 * 
 */
package co.innovate.rentavoz.services.venta.item;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemService
 * @date 15/01/2014
 *
 */
public interface VentaItemService  extends GenericService<VentaItem, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/01/2014
	* @param start
	* @param end
	* @return
	*/
	public List<VentaItem> findVentaByFechas(Date start, Date end,int firstResult,int maxResults);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/01/2014
	* @param start
	* @param end
	* @return
	*/
	public int countFindVentaByFechas(Date start, Date end);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 8/06/2014
	* @param consecutivo
	* @return
	*/
	VentaItem findByNumeroFactura(String consecutivo);
}
