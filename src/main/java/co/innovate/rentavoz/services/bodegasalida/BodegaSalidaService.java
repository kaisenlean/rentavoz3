/**
 * 
 */
package co.innovate.rentavoz.services.bodegasalida;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaService
 * @date 14/01/2014
 *
 */
public interface BodegaSalidaService extends GenericService<BodegaSalida, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/01/2014
	* @param start
	* @param end
	* @return
	*/
	List<BodegaSalidaReferencia> findByFechas(Date start, Date end);
}
