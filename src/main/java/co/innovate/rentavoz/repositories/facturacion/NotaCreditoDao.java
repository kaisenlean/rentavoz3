/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion;

import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.facturacion.NotaCredito;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoDao
 * @date 11/03/2014
 *
 */
public interface NotaCreditoDao  extends GenericRepository<NotaCredito, Integer>{

	
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
