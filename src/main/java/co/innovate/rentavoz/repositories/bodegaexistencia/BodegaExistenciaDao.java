/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaexistencia;

import java.util.List;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaDao
 * @date 13/01/2014
 *
 */
public interface BodegaExistenciaDao extends GenericRepository<BodegaExistencia, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param productoId
	* @return
	*/
	BodegaExistencia findByBarcode(String productoId);
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param productoId
	* @param sucursal
	* @return
	*/
	BodegaExistencia findByBarcode(String productoId,Sucursal sucursal);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param productoId
	* @return
	*/
	BodegaExistencia findByBarcodeActivo(String productoId);
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param productoId
	* @param sucursal
	* @return
	*/
	BodegaExistencia findByBarcodeActivo(String productoId,Sucursal sucursal);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param sucursal
	* @param bodegaItem
	* @return
	*/
	List<BodegaExistencia> findByItemAndSucursal(Sucursal sucursal, BodegaItem bodegaItem);
	
	
	
	/**
	 * Método que consulta las existencias por el ingreso que las agrupa
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/01/2014
	* @param bodegaIngreso
	* @return
	*/
	List<BodegaExistencia> findByIngreso(BodegaIngreso bodegaIngreso);
}
