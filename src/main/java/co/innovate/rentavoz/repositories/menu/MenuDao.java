/**
 * 
 */
package co.innovate.rentavoz.repositories.menu;

import java.util.List;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.repositories.GenericRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class MenuDao
 * @date 12/01/2014
 * 
 */
public interface MenuDao extends GenericRepository<Menu, Integer> {

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
