/**
 * 
 */
package co.innovate.rentavoz.repositories.tarea;

import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaDao
 * @date 14/03/2014
 *
 */
public interface TareaDao extends GenericRepository<Tarea, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/03/2014
	* @param centropeEnum
	* @return
	*/
	List<Tarea> findTareasPendientes(TareaCentropeEnum centropeEnum) throws BaseException;
}
