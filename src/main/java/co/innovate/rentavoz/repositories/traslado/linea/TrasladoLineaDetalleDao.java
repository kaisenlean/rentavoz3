/**
 * 
 */
package co.innovate.rentavoz.repositories.traslado.linea;

import java.util.List;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLineaDetalle;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaDetalleDao
 * @date 17/02/2014
 *
 */
public interface TrasladoLineaDetalleDao extends GenericRepository<TrasladoLineaDetalle, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param trasladoLinea
	* @return
	*/
	List<TrasladoLineaDetalle> findByTraslado(TrasladoLinea trasladoLinea);
}
