/**
 * 
 */
package co.innovate.rentavoz.repositories.planlinea;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.PlanLinea;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanLineaDao
 * @date 13/01/2014
 *
 */
public interface PlanLineaDao  extends GenericRepository<PlanLinea, Integer>{
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param l
	*/
	void desactivarTodosPlanesLineas(Linea l);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param l
	* @param p
	*/
	void activarPorLineaYPlan(Linea l, Plan p);
}
