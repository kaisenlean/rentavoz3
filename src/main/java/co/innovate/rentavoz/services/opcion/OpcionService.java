/**
 * 
 */
package co.innovate.rentavoz.services.opcion;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionService
 * @date 18/01/2014
 *
 */
public interface OpcionService extends GenericService<Opcion, String> {

	/**
	 * Método que consulta una opcion por una clave como parametro
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 18/01/2014
	* @param clave
	* @return
	*/
	Opcion findByClave(String clave) throws BaseException;
}
