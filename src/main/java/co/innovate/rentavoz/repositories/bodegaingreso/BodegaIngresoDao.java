/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaingreso;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaIngresoDao
 * @date 13/01/2014
 *
 */
public interface BodegaIngresoDao extends GenericRepository<BodegaIngreso, Integer> {

	
	
	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param start
	* @param end
	* @return
	*/
	List<BodegaExistencia> findByFechas(Date start, Date end);
}
