/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.linea.consumo;

import java.util.List;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoDao
 * @date 19/02/2014
 *
 */
public interface LineaConsumoDao extends GenericRepository<LineaConsumo, Integer>{

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 20/02/2014
	* @param linea
	* @return
	*/
	List<LineaConsumo> findByLinea(Linea linea);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 25/02/2014
	* @param fechaFacturacion
	* @return
	*/
	List<Linea> findLineasNoVendidasConConsumo(FechaFacturacion fechaFacturacion,int corte);
	
	

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 20/02/2014
	* @param linea
	* @return
	*/
	LineaConsumo findULtimoConsumoByLinea(Linea linea);
}
