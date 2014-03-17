/**
 * 
 */
package co.innovate.rentavoz.repositories.log.linea;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LogLineaDao
 * @date 16/03/2014
 *
 */
public interface LogLineaDao extends GenericRepository<LogLinea, Integer> {

	
	
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
}
