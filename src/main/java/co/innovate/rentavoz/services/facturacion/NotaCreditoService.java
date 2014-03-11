/**
 * 
 */
package co.innovate.rentavoz.services.facturacion;

import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.facturacion.NotaCredito;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoService
 * @date 11/03/2014
 *
 */
public interface NotaCreditoService extends GenericService<NotaCredito, Integer>{

	
	/**
	  * Consulta las notas credito por criterio
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 11/03/2014
	* @param query
	* @param firstResult
	* @param maxResults
	* @param order
	* @return
	*/
	List<NotaCredito> findByCriteria(String query, int firstResult,
				int maxResults,Order order);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 11/03/2014
	* @param query
	* @param firstResult
	* @param maxResults
	* @param order
	* @return
	*/
	int countByCriteria(String query);
}
