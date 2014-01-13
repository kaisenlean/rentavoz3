/**
 * 
 */
package co.innovate.rentavoz.repositories.simcard;

import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SimcardDao
 * @date 13/01/2014
 *
 */
public interface SimcardDao extends GenericRepository<Simcard, Integer>{

	
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
