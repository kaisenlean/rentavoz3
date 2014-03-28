/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.facturacion.NotaDebito;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoDao
 * @date 11/03/2014
 *
 */
public interface NotaDebitoDao  extends GenericRepository<NotaDebito, Integer>{

	
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
	List<NotaDebito> findByCriteria(String query, int firstResult,
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
	List<NotaDebito> findByFecha(Date fecha);
	
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaDebito> findByGenerador(Tercero tercero);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaDebito> findByGenerador(Tercero tercero,Date fecha);
	
	
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
	double sumByGenerador(Tercero tercero,Date date);
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaDebito> findBySucursal(Sucursal sucursal);
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	List<NotaDebito> findBySucursal(Sucursal sucursal,Date fecha);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	double sumBySucursal(Sucursal sucursal);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/03/2014
	* @param tercero
	* @return
	*/
	double sumBySucursal(Sucursal sucursal,Date fecha);
}
