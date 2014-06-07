/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaService
 * @date 5/02/2014
 *
 */
public interface VentaLineaDao extends GenericRepository<VentaLinea, Integer>{

	
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
	* @date 23/04/2014
	* @param fechaFacturacion
	* @return
	*/
	List<Venta> findVentaAjuste(FechaFacturacion fechaFacturacion,Boolean ajuste);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 25/04/2014
	* @param fechaFacturacion
	* @param ajuste
	* @return
	*/
	 Double findValorVentaAjuste(FechaFacturacion fechaFacturacion,Boolean ajuste) ;
	 
	 
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
	List<VentaLinea> findByCriterio(int firstResul, int maxResults , Order order , String numeroLinea, Tercero cliente , int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim,String modoPago);
	
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
	int countdByCriterio(String numeroLinea, Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim,String modoPago);
	
	
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
	double sumByCriterio(String numeroLinea, Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim,String modoPago);
	
	
	
	
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
	double sumByCriterioCompra(String numeroLinea,Tercero cliente, int corte,FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim,String modoPago);
	 
}
