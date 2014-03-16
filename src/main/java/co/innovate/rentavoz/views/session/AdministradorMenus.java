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
import co.innovate.rentavoz.services.permiso.UsuarioMenuService;
import co.innovate.rentavoz.views.BaseBean;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AdministradorMenus
 * @date 14/07/2013
 * 
 */

@ManagedBean
@SessionScoped
public class AdministradorMenus extends BaseBean implements Serializable {

	/**
	 * 14/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * 17/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * MENU_RAIZ
	 */
	private static final String MENU_RAIZ = "root";



	private List<Menu> menus=new ArrayList<Menu>();
	
	
	
	@ManagedProperty(value="#{menuService}")
	private MenuService menuService;
	
	@ManagedProperty(value="#{usuarioMenuService}")
	private UsuarioMenuService usuarioMenuService;
	
	@ManagedProperty(value="#{login}")
	private Login login;


	private MenuListener listener;
	private String padre;

	private List<Menu> menuPrincipal= new ArrayList<Menu>();
	@PostConstruct
	public void init() {
		listener = new MenuListener() {

			@Override
			public void ejecutarAccion() {

				cargarMenus(padre);
			}
		};
if (login.getUser()!=null) {
	if (login.getUser().getAdministrador()) {
		
		menuPrincipal=(menuService.findTodosByPadre(MENU_RAIZ));
	}else{
		menuPrincipal=usuarioMenuService.findByUsuario(login.getUser(),MENU_RAIZ);
	}

}else{
	
	mensajeError("Por favor inicia sesion nuevamente");
}
	

	}

	public void cargarMenus(String padre) {
		menus.clear();
		try {
			if (login.getUser().getAdministrador()) {
			menus = menuService.findTodosByPadre(padre);
			}else{
				menus=usuarioMenuService.findByUsuario(login.getUser(), padre);
			}
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

	public void cargar(String direccion){
		
		goTo(direccion);
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

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/01/2014
	* @return the menuPrincipal
	*/
	public List<Menu> getMenuPrincipal() {
		return menuPrincipal;
	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/01/2014
	* @param menuPrincipal the menuPrincipal to set
	*/
	public void setMenuPrincipal(List<Menu> menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param usuarioMenuService the usuarioMenuService to set
	 */
	public void setUsuarioMenuService(UsuarioMenuService usuarioMenuService) {
		this.usuarioMenuService = usuarioMenuService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
