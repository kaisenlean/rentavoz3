/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaService
 * @date 5/02/2014
 *
 */
public interface VentaLineaService extends GenericService<VentaLinea, Integer> {

	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param venta
	* @return
	*/
	List<VentaLinea> findByVenta(Venta venta);
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	* @param linea
	* @return
	*/
	List<VentaLinea> findHistorialFacturacion(Linea linea);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/03/2014
	* @param tercero
	* @return
	*/
	List<VentaLinea> findLineasConDevolucionByCliente(Tercero tercero);
	
	 /**
		* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
		* @date 26/05/2014
		* @param firstResul
		* @param maxResults
		* @param order
		* @param numeroLinea
		* @param cliente
		* @param corte
		* @return
		*/
		List<VentaLinea> findByCriterio(int firstResul, int maxResults , Order order , String numeroLinea, Tercero cliente , int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim);
		
		/**
		* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
		* @date 26/05/2014
		* @param numeroLinea
		* @param cliente
		* @param corte
		* @param fechaFacturacion
		* @param fecha
		* @return
		*/
		int countdByCriterio(String numeroLinea, Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim);
		
		
		/**
		* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
		* @date 27/05/2014
		* @param numeroLinea
		* @param cliente
		* @param corte
		* @param fechaFacturacion
		* @param fecha
		* @return
		*/
		double sumByCriterio(String numeroLinea, Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim);
		
		
		
		
		/**
		* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
		* @date 27/05/2014
		* @param numeroLinea
		* @param cliente
		* @param corte
		* @param fechaFacturacion
		* @param fecha
		* @return
		*/
		double sumByCriterioCompra(String numeroLinea,Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim);
		 
}
