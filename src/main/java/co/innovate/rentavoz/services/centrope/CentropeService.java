/**
 * 
 */
package co.innovate.rentavoz.services.centrope;

import java.util.List;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CentropeService
 * @date 26/01/2014
 *
 */
public interface CentropeService extends GenericService<Centrope, Integer>{

	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	* @param parametro
	* @return
	*/
	List<Centrope> findByParametro(String parametro);
}
