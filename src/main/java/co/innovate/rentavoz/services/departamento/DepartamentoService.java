/**
 * 
 */
package co.innovate.rentavoz.services.departamento;

import java.util.List;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class DepartamentoService
 * @date 13/01/2014
 *
 */
public interface DepartamentoService extends GenericService<Departamento, Integer> {

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @return
	*/
	public List<Departamento> findByCriterio(String criterio);
}
