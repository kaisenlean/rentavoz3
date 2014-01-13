/**
 * 
 */
package co.innovate.rentavoz.services.operador;

import java.util.List;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OperadorService
 * @date 13/01/2014
 *
 */
public interface OperadorService extends GenericService<Operador, Integer > {
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @return
	*/
	public List<Operador> findByCriterio(String criterio) ;
}
