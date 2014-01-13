/**
 * 
 */
package co.innovate.rentavoz.services.caja;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.caja.Caja;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class CajaService
 * @date 12/01/2014
 *
 */
public interface CajaService extends GenericService<Caja, Integer>
{


	 /**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @param usuario
	*/
	void abrirCaja(Usuario usuario) throws BaseException ;
	
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @return
	*/
	 double valorCaja() throws BaseException;

	
}
