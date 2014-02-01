/**
 * 
 */
package co.innovate.rentavoz.repositories.permiso;

import java.util.List;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.model.permiso.UsuarioMenu;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UsuarioMenuDao
 * @date 31/01/2014
 *
 */
public interface UsuarioMenuDao extends GenericRepository<UsuarioMenu, Integer> {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/02/2014
	* @param usuario
	* @param menu
	* @return
	*/
	UsuarioMenu findByUsuarioAndMenu(Usuario usuario,Menu menu);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/02/2014
	* @param user
	* @return
	*/
	List<Menu> findByUsuario(Usuario user,String padre);
}
