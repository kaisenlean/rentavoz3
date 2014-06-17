/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDao
 * @date 15/01/2014
 *
 */
public interface VentaItemDao extends GenericRepository<VentaItem, Integer> {

	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/01/2014
	* @param start
	* @param end
	* @return
	*/
	 List<VentaItem> findVentaByFechas(Date start, Date end,int firstResult,int maxResults);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/01/2014
	* @param start
	* @param end
	* @return
	*/
	 int countFindVentaByFechas(Date start, Date end);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 8/06/2014
	* @param consecutivo
	* @return
	*/
	VentaItem findByNumeroFactura(String consecutivo);
	
	
	
	
}