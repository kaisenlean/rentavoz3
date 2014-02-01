/**
 * 
 */
package co.innovate.rentavoz.logic.permiso;

import org.primefaces.model.TreeNode;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.profile.Usuario;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ControladorMenuUsuario
 * @date 31/01/2014
 *
 */
public interface ControladorMenuUsuario {

	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 31/01/2014
	* @return
	*/
	 TreeNode cargarArbolPermisos(Usuario usuario);
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 31/01/2014
	* @param root
	*/
	void guardarPermisos(TreeNode root,Usuario usuario) throws BaseException;

}
