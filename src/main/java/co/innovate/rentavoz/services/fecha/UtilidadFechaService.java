/**
 * 
 */
package co.innovate.rentavoz.services.fecha;

import java.util.Date;

import co.innovate.rentavoz.exception.BaseException;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UtilidadFechaService
 * @date 26/01/2014
 *
 */
public interface UtilidadFechaService {

	
	
	/**
	 * Método que extrae el texto de los meses del rango de fechas seleccionados
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 26/01/2014
	* @param inicio
	* @param fin
	* @return
	*/
	public String obtenerMesesPorRangoDeFechas(Date inicio,Date fin) throws BaseException;
}
