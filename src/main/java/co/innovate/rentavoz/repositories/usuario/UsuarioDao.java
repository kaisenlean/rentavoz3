package co.innovate.rentavoz.repositories.usuario;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz-3
* @class UsuarioDao
* @date 12/01/2014
*
*/
public interface UsuarioDao extends GenericRepository<Usuario, String> {

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @return
	* @throws BaseException
	*/
	Usuario login(String login , String contrasena) throws BaseException;
}
