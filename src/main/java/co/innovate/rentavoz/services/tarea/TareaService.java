/**
 * 
 */
package co.innovate.rentavoz.services.tarea;

import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaService
 * @date 14/03/2014
 *
 */
public interface TareaService  extends GenericService<Tarea, Integer>{


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/03/2014
	* @param centropeEnum
	* @return
	*/
	List<Tarea> findTareasPendientes(TareaCentropeEnum centropeEnum) throws BaseException;
}
