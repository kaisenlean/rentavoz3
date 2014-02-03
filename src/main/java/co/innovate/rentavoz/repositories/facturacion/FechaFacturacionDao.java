/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacionDao
 * @date 26/01/2014
 *
 */
public interface FechaFacturacionDao extends GenericRepository<FechaFacturacion, Integer> {

	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/02/2014
	* @return
	*/
	public List<FechaFacturacion> findActivas();


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/02/2014
	* @return
	*/
	public FechaFacturacion findByFecha(Date fecha);

}
