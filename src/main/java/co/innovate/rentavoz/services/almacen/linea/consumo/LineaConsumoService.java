/**
 * 
 */
package co.innovate.rentavoz.services.almacen.linea.consumo;

import java.util.List;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoService
 * @date 19/02/2014
 *
 */
public interface LineaConsumoService extends GenericService<LineaConsumo, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 20/02/2014
	* @param linea
	* @return
	*/
	List<LineaConsumo> findByLinea(Linea linea);
}
