/**
 * 
 */
package co.innovate.rentavoz.services.ajustefacturacion;

import java.util.List;

import co.innovate.rentavoz.model.ajustefactura.AjusteFacturaDto;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class AjusteFacturacionService
 * @date 23/04/2014
 *
 */
public interface AjusteFacturacionService {

	List<AjusteFacturaDto> cargarAjuste(FechaFacturacion fechaFacturacion,double meta);
	
}
