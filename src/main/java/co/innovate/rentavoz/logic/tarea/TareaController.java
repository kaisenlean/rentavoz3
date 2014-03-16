/**
 * 
 */
package co.innovate.rentavoz.logic.tarea;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.model.venta.VentaItem;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaController
 * @date 14/03/2014
 *
 */
public interface TareaController {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/03/2014
	* @param venta
	* @param solicitante
	* @param centropeEnum
	*/
	void crearTareaAnulacionVenta(Venta venta,Tercero solicitante,TareaCentropeEnum centropeEnum) throws BaseException;
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 16/03/2014
	* @param venta
	* @param solicitante
	* @param centropeEnum
	* @throws BaseException
	*/
	void crearTareaAnulacionVenta(VentaItem venta,Tercero solicitante,TareaCentropeEnum centropeEnum) throws BaseException;
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/03/2014
	* @param tarea
	* @param finaliza
	*/
	void finalizarTarea(Tarea tarea , Tercero finaliza);
	
	
	
}
