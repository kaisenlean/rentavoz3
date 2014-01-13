/**
 * 
 */
package co.innovate.rentavoz.repositories.ciudad;

import java.util.List;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CiudadDao
 * @date 13/01/2014
 *
 */
public interface CiudadDao  extends GenericRepository<Ciudad, Integer>{


	
	
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @param departamento
	* @return
	*/
	List<Ciudad> findByCriterio(String criterio,
			Departamento departamento);
}
