/**
 * 
 */
package co.innovate.rentavoz.services.notificacioncuota;

import java.util.Date;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCuotaService
 * @date 4/03/2014
 *
 */
public interface EnvioCuotaService {

	
	/**
	 * Método que envia los deudores morosos hasta la fecha especificada
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	* @param date
	*/
	void enviarDeudoresMorososHastaLaFecha(Date date);
}
