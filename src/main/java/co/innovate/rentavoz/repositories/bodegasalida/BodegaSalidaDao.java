/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegasalida;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaDao
 * @date 14/01/2014
 *
 */

public interface BodegaSalidaDao extends GenericRepository<BodegaSalida, Integer> {

	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/01/2014
	* @param start
	* @param end
	* @return
	*/
	List<BodegaSalidaReferencia> findByFechas(Date start, Date end);
}
