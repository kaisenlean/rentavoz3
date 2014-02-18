/**
 * 
 */
package co.innovate.rentavoz.services.traslado.linea;

import java.util.List;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLineaDetalle;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaDetalleService
 * @date 17/02/2014
 *
 */
public interface TrasladoLineaDetalleService  extends GenericService<TrasladoLineaDetalle, Integer>{

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param trasladoLinea
	* @return
	*/
	List<TrasladoLineaDetalle> findByTraslado(TrasladoLinea trasladoLinea);
}
