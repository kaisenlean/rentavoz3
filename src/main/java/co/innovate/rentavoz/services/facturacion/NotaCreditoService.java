/**
 * 
 */
package co.innovate.rentavoz.services.facturacion;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
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
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param fecha
	* @return
	*/
	public List<NotaCredito> findByFecha(Date fecha);
	
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaCredito> findByGenerador(Tercero tercero);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	double sumByGenerador(Tercero tercero);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaCredito> findBySucursal(Sucursal sucursal);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	double sumBySucursal(Sucursal sucursal);
}
