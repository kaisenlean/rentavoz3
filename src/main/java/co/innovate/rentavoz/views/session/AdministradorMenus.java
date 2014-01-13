/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.services.menu.MenuService;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AdministradorMenus
 * @date 14/07/2013
 * 
 */

@ManagedBean(eager = true)
@SessionScoped
public class AdministradorMenus implements Serializable {

	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 988905964589622257L;
	private List<Menu> menus;
	
	@ManagedProperty(value="#{menuService}")
	private MenuService menuService;


	private MenuListener listener;
	private String padre;

	@PostConstruct
	public void init() {
		listener = new MenuListener() {

			@Override
			public void ejecutarAccion() {

				cargarMenus(padre);
			}
		};
		try {
			menus = menuService.findAll();
		} catch (Exception e) {
			menus = new ArrayList<Menu>();
		}
	}

	public void cargarMenus(String padre) {
		menus.clear();
		try {
			menus = menuService.findTodosByPadre(padre);
		} catch (Exception e) {
			menus = new ArrayList<Menu>();
		}
	}

	public MenuListener getListener() {
		return listener;
	}

	public void setListener(MenuListener listener) {
		this.listener = listener;
	}

	

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return
	 */
	public String getPadre() {
		return padre;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param padre
	 */
	public void setPadre(String padre) {
		this.padre = padre;
	}

	// </editor-fold>
}
