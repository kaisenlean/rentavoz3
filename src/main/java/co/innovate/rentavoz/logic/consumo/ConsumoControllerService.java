/**
 * 
 */
package co.innovate.rentavoz.logic.consumo;

import java.util.List;

import co.innovate.rentavoz.model.almacen.LineaConsumo;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsumoControllerService
 * @date 19/02/2014
 *
 */
public interface ConsumoControllerService {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 19/02/2014
	* @param pathFile
	* @return
	*/
	List<LineaConsumo> cargarConsumosClaro(String pathFile);
}
