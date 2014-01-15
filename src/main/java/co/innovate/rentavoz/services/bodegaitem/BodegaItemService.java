/**
 * 
 */
package co.innovate.rentavoz.services.bodegaitem;

import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaItemService
 * @date 13/01/2014
 *
 */
public interface BodegaItemService extends GenericService<BodegaItem, Integer>{

	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param ref
	* @return
	*/
	boolean existReferencia(String ref);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param param
	* @return
	*/
	List<BodegaItem> findByCriterio(String param);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param nombre
	* @return
	*/
	BodegaItem findByNombre(String nombre);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/01/2014
	* @param bodegaItem
	*/
	void updatePrecios(BodegaItem bodegaItem);
}
