/**
 * 
 */
package co.innovate.rentavoz.services.notificacioncaja;

import co.innovate.rentavoz.exception.BaseException;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCierreCajaService
 * @date 1/03/2014
 *
 */
public interface EnvioCierreCajaService {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/03/2014
	*/
	public void enviarCierreCajaAdministrativo()throws BaseException;
}
