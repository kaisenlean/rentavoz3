/**
 * 
 */
package co.innovate.rentavoz.services.reportes.caja;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.services.reportes.caja.dto.ReporteCajaDto;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ReporteCajaService
 * @date 27/02/2014
 *
 */
public interface ReporteCajaService {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/02/2014
	* @param sucursal
	* @param fechaCierre
	* @return
	*/
	List<ReporteCajaDto> reporteCajasBySucursal(Sucursal sucursal , Date fechaCierre);
	
}
