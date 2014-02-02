/**
 * 
 */
package co.innovate.rentavoz.services.ciudad;

import java.util.List;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CiudadService
 * @date 13/01/2014
 *
 */
public interface CiudadService extends GenericService<Ciudad, Integer> {

	
	
	
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @param departamento
	* @return
	*/
	List<Ciudad> findByCriterio(String criterio,
			Departamento departamento);
	
	


	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/02/2014
	* @param criterio
	* @return
	 */
	List<Ciudad> findByCriterio(String criterio);
}
