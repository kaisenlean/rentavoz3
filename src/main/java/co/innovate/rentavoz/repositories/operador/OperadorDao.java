/**
 * 
 */
package co.innovate.rentavoz.repositories.operador;

import java.util.List;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OperadorDao
 * @date 13/01/2014
 *
 */
public interface OperadorDao  extends GenericRepository<Operador, Integer>{
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @return
	*/
	public List<Operador> findByCriterio(String criterio) ;
}
