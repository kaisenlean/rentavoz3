/**
 * 
 */
package co.innovate.rentavoz.services.bodegaingreso;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaIngresoService
 * @date 13/01/2014
 *
 */
public interface BodegaIngresoService extends GenericService<BodegaIngreso , Integer> {

	
	
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param start
	* @param end
	* @return
	*/
	List<BodegaExistencia> findByFechas(Date start, Date end) ;
}
