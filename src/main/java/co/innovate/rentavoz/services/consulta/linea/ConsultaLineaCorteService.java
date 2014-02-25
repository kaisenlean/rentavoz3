/**
 * 
 */
package co.innovate.rentavoz.services.consulta.linea;

import java.util.List;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsultaLineaCorteService
 * @date 23/02/2014
 *
 */
public interface ConsultaLineaCorteService {

	
	/**
	 * Método que consulta las lineas en estado repo no facturadas en un perido de facturacion en comun
	 *que tienen consumos registrados en la plataforma de los servidores de telefonia movil 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 23/02/2014
	* @return
	*/
	List<Linea> findLineasRepoConsumos(FechaFacturacion fechaFacturacion);
}
