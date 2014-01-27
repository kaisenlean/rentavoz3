/**
 * 
 */
package co.innovate.rentavoz.repositories.centrope;

import java.util.List;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CentropeDao
 * @date 26/01/2014
 *
 */
public interface CentropeDao extends GenericRepository<Centrope, Integer> {

	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	* @param parametro
	* @return
	*/
	List<Centrope> findByParametro(String parametro);
}
