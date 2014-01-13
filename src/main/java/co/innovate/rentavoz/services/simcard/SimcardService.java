/**
 * 
 */
package co.innovate.rentavoz.services.simcard;

import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SimcardService
 * @date 13/01/2014
 *
 */
public interface SimcardService extends GenericService<Simcard, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param criteria
	* @return
	*/
	 List<Simcard> findDisponibles(String criteria);
	 
	 
	  /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param simIccid
	* @return
	* @throws BaseException
	*/
	Simcard findByScId(String simIccid) throws BaseException;
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param simIccid
	* @return
	* @throws BaseException
	*/
	List<Simcard> getByScId(String simIccid) throws BaseException; 
}
