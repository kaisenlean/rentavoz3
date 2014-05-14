/**
 * 
 */
package co.innovate.rentavoz.services.bodegaexistencia;

import java.util.List;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaService
 * @date 13/01/2014
 *
 */
public interface BodegaExistenciaService extends GenericService<BodegaExistencia, Integer> {
	
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
	BodegaExistencia findByBarcode(String productoId,List<Sucursal> sucursal);
	
	
	
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
	BodegaExistencia findByBarcodeActivo(String productoId,List<Sucursal> sucursal);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 13/01/2014
	* @param sucursal
	* @param bodegaItem
	* @return
	*/
	List<BodegaExistencia> findByItemAndSucursal(List<Sucursal> sucursal, BodegaItem bodegaItem);
	
	
	
	/**
	 * Método que consulta las existencias por el ingreso que las agrupa
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/01/2014
	* @param bodegaIngreso
	* @return
	*/
	List<BodegaExistencia> findByIngreso(BodegaIngreso bodegaIngreso);
	
	
	/**
	 * Elimina las existencias por un ingreso determinado
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 23/01/2014
	* @param bodegaIngreso
	*/
	void deleteFromBodegaIngreso(BodegaIngreso bodegaIngreso);
}
