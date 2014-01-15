/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegasalida.referencia;

import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaReferenciaDao
 * @date 14/01/2014
 *
 */
public interface BodegaSalidaReferenciaDao  extends GenericRepository<BodegaSalidaReferencia, Integer>  {

	
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/01/2014
	* @param bodegaSalida
	* @return
	*/
	List<BodegaSalidaReferencia> findByBodegaSalida(BodegaSalida bodegaSalida);
}
