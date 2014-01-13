/**
 * 
 */
package co.innovate.rentavoz.services.menu;

import java.util.List;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.services.GenericService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class MenuService
 * @date 12/01/2014
 *
 */
public interface MenuService extends GenericService<Menu, Integer>{

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @return
	*/
	 List<Menu> findTodos();

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 12/01/2014
	* @param padre
	* @return
	*/
	 List<Menu> findTodosByPadre(String padre);
}
