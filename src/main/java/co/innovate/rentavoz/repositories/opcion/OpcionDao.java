/**
 * 
 */
package co.innovate.rentavoz.repositories.opcion;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionDao
 * @date 18/01/2014
 *
 */
public interface OpcionDao  extends GenericRepository<Opcion, String>{

	
	
	/**
	 * Método que consulta una opcion por una clave como parametro
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 18/01/2014
	* @param clave
	* @return
	*/
	Opcion findByClave(String clave) throws BaseException;
}
