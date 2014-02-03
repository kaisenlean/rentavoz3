package co.innovate.rentavoz.services.usuario;

import java.util.List;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.GenericService;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz-3
* @class UsuarioService
* @date 12/01/2014
*
*/
public interface UsuarioService extends GenericService<Usuario, String> {


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @return
	* @throws BaseException
	*/
	Usuario login(String login , String contrasena) throws BaseException;

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 3/02/2014
	* @param query
	* @return
	*/
	List<Usuario> findByCriterio(String query);
}
