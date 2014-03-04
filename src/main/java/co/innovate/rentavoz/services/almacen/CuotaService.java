/**
 * 
 */
package co.innovate.rentavoz.services.almacen;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuotaService
 * @date 7/02/2014
 *
 */
public interface CuotaService extends GenericService<Cuota, Integer>{

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 9/02/2014
	* @param cliente
	* @return
	*/
	List<Cuota> buscarCuotasPendientesPorCliente(Tercero cliente);
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 9/02/2014
	* @param cobrador
	* @return
	*/
	List<Cuota> buscarRutaDeCuotasPorCobrador(Tercero cobrador);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 9/02/2014
	* @param cobrador
	* @return
	*/
	List<Cuota> findByVenta(Venta venta);
	
	
	/**
	 * Método que consulta los clientes que deben cuotas en base a una fecha de cierre de las mismas
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	* @param fechaCierre
	* @return
	*/
	List<Tercero> findDeudoresMorosos(Date fechaCierre);
}
