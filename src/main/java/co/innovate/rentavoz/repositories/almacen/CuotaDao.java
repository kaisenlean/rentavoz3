/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen;

import java.util.List;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuotaDao
 * @date 7/02/2014
 *
 */
public interface CuotaDao extends GenericRepository<Cuota, Integer>{

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
	
	
}
