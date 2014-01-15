/**
 * 
 */
package co.innovate.rentavoz.services.bodegasalida.referencia;

import java.util.List;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaReferenciaService
 * @date 14/01/2014
 *
 */
public interface BodegaSalidaReferenciaService extends GenericService<BodegaSalidaReferencia, Integer> {

	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/01/2014
	* @param bodegaSalida
	* @return
	*/
	List<BodegaSalidaReferencia> findByBodegaSalida(BodegaSalida bodegaSalida);
}
