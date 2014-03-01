/**
 * 
 */
package co.innovate.rentavoz.repositories.caja;

import java.util.Date;
import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.caja.Caja;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class CajaDao
 * @date 12/01/2014
 * 
 */
public interface CajaDao extends GenericRepository<Caja, Integer> {

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param usuario
	 */
	void abrirCaja(Usuario usuario) throws BaseException;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return
	 */
	double valorCaja(Tercero vendedor) throws BaseException;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return
	 */
	double valorCajaLineas(Tercero vendedor) throws BaseException;

	/**
	 * Método que consulta el valor de la caja del dia por sucursal
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursal
	 * @return
	 */
	double valorCajaLineasBySucursal(Sucursal sucursal, Date fecha);
	
	/**
	 * Método que consulta el valor de la caja del dia por sucursal detallando la lista de cuotas
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursal
	 * @return
	 */
	List<Cuota> valorCajaLineasBySucursalDetalle(Sucursal sucursal, Date fecha);
	
	
	
	/**
	 * Método que consulta el valor de la caja del dia por sucursal
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursal
	 * @return
	 */
	double valorCajaEquiposBySucursal(Sucursal sucursal, Date fecha);
	
	/**
	 * Método que consulta el valor de la caja del dia por sucursal detallando la lista de cuotas
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursal
	 * @return
	 */
	List<VentaItemCuota> valorCajaEquiposBySucursalDetalle(Sucursal sucursal, Date fecha);
}
