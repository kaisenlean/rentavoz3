/**
 * 
 */
package co.innovate.rentavoz.services.notificacionlinea;

import co.innovate.rentavoz.exception.BaseException;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoService
 * @date 25/02/2014
 *
 */
public interface EnvioLineaConsumoService {

	
	/**
	 * Método que envia por correo electronico las lineas sin vender que presentan consumos
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 25/02/2014
	* @throws BaseException
	*/
	void enviarNotificacionLineasConsumoNoVendidas() throws BaseException;
}
