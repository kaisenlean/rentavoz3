/**
 * 
 */
package co.innovate.rentavoz.repositories.sucursaltercero;

import java.util.List;

import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalTerceroDao
 * @date 2/02/2014
 *
 */
public interface SucursalTerceroDao  extends GenericRepository<SucursalTercero, Integer>{

	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/02/2014
	* @return
	*/
	List<SucursalTercero> findByTercero(Tercero tercero);
	
	
	
	
}
