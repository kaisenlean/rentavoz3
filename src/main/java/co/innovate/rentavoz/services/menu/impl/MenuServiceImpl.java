/**
 * 
 */
package co.innovate.rentavoz.services.menu.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.menu.MenuDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.menu.MenuService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class MenuServiceImpl
 * @date 12/01/2014
 *
 */
@Service(MenuServiceImpl.SERVICE_NAME)
public  class MenuServiceImpl extends GenericServiceImpl<Menu, Integer> implements MenuService,Serializable {

	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SERVICE_NAME
	 */
	static final String SERVICE_NAME = "menuService";


	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private MenuDao menuDao;
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.menu.MenuService#findTodos()
	 */
	@Override
	public List<Menu> findTodos() {
		return menuDao.findTodos();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.menu.MenuService#findTodosByPadre(java.lang.String)
	 */
	@Override
	public List<Menu> findTodosByPadre(String padre) {
		return menuDao.findTodosByPadre(padre);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Menu, Integer> getDao() {
		return menuDao;
	}

}
