/**
 * 
 */
package co.innovate.rentavoz.services.facturacion;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TalonarioService
 * @date 21/04/2014
 *
 */

public interface TalonarioService extends GenericService<Talonario, String> {

	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/04/2014
	* @param sucursal
	* @return
	*/
	Talonario cargarConsecutivoFactura(Sucursal sucursal)throws BaseException;
}
