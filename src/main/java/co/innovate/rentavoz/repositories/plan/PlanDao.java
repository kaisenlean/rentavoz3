/**
 * 
 */
package co.innovate.rentavoz.repositories.plan;

import java.util.List;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanDao
 * @date 13/01/2014
 *
 */
public interface PlanDao extends GenericRepository<Plan, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criterio
	* @return
	*/
	public List<Plan> findByCriterio(String criterio);
}
