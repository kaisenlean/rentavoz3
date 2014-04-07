/**
 * 
 */
package co.innovate.rentavoz.services.log.linea;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LogLineaService
 * @date 16/03/2014
 *
 */
public interface LogLineaService extends GenericService<LogLinea, Integer> {

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 16/03/2014
	* @param start
	* @param end
	* @param numeroLinea
	* @param creador
	* @param accionLineaEnum
	* @return
	*/
	List<LogLinea> findByCriterio(Date start , Date end , String numeroLinea,Tercero creador,AccionLineaEnum accionLineaEnum,int maxRegistros , int registroInicial,Order order);
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 16/03/2014
	* @param start
	* @param end
	* @param numeroLinea
	* @param creador
	* @param accionLineaEnum
	* @return
	*/
	int countByCriterio(Date start , Date end , String numeroLinea,Tercero creador,AccionLineaEnum accionLineaEnum);
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 31/03/2014
	*/
	List<LogLinea>  cargarLineaScid(String pathFile,Tercero tercero);
}
