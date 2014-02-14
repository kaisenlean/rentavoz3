/**
 * 
 */
package co.innovate.rentavoz.logic.permiso.impl;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.permiso.ControladorMenuUsuario;
import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.model.permiso.UsuarioMenu;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.menu.MenuService;
import co.innovate.rentavoz.services.permiso.UsuarioMenuService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ControladorMenuUsuarioImpl
 * @date 31/01/2014
 * 
 */
@Service("controladorMenuUsuario")
public class ControladorMenuUsuarioImpl implements ControladorMenuUsuario {

	/**
	 * 31/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ROOT2
	 */
	private static final String ROOT = "root";

	@Autowired
	private UsuarioMenuService usuarioMenuService;

	@Autowired
	private MenuService menuService;

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.logic.permiso.ControladorMenuUsuario#cargarArbolPermisos()
	 */
	@Override
	public TreeNode cargarArbolPermisos(Usuario usuario) {
		DefaultTreeNode root = new DefaultTreeNode(ROOT, null);

		List<Menu> menus = menuService.cargarMenusPadre();
		ArrayList<TreeNode> nodos = new ArrayList<TreeNode>();
		for (Menu menu : menus) {
			UsuarioMenu usuarioMenu = usuarioMenuService.findByUsuarioAndMenu(
					usuario, menu);
			menu.setUsuarioMenu(usuarioMenu);
			if (usuarioMenu != null) {
				menu.setCrea(usuarioMenu.getAcceso().contains("C"));
				menu.setEdita(usuarioMenu.getAcceso().contains("E"));
				menu.setElimina(usuarioMenu.getAcceso().contains("B"));
				menu.setImprime(usuarioMenu.getAcceso().contains("I"));
			}
			DefaultTreeNode nodoHijo = new DefaultTreeNode(menu, root);
			if (usuarioMenu != null) {
				nodoHijo.setSelected(true);
				
			}
			cargarHijosByṔadre(nodoHijo, menu, usuario);
			nodoHijo.setExpanded(true);
			nodos.add(nodoHijo);

		}
		
		root.setChildren(nodos);
		return root;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param nodoHijo
	 */
	private void cargarHijosByṔadre(DefaultTreeNode nodoPadre, Menu menu,
			Usuario usuario) {

		List<Menu> menus = menuService.findTodosByPadre(menu.getParametro());

		if (menus.isEmpty()) {
			return;
		}
		ArrayList<TreeNode> nodos = new ArrayList<TreeNode>();
		for (Menu menuTemp : menus) {
			UsuarioMenu usuarioMenu = usuarioMenuService.findByUsuarioAndMenu(
					usuario, menuTemp);
			menuTemp.setUsuarioMenu(usuarioMenu);
			if (usuarioMenu != null) {
				menuTemp.setCrea(usuarioMenu.getAcceso().contains("C"));
				menuTemp.setEdita(usuarioMenu.getAcceso().contains("E"));
				menuTemp.setElimina(usuarioMenu.getAcceso().contains("B"));
				menuTemp.setImprime(usuarioMenu.getAcceso().contains("I"));
			}
			DefaultTreeNode nodoHijo = new DefaultTreeNode(menuTemp, nodoPadre);

			if (usuarioMenu != null) {
				nodoHijo.setSelected(true);
				nodoPadre.setSelected(true);
			}
			nodos.add(nodoHijo);

		}

		nodoPadre.setChildren(nodos);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.logic.permiso.ControladorMenuUsuario#guardarPermisos
	 * (org.primefaces.model.TreeNode)
	 */
	@Override
	public void guardarPermisos(TreeNode root, Usuario usuario)
			throws BaseException {
		List<TreeNode> hijos = root.getChildren();
		for (TreeNode treeNode : hijos) {
			if (treeNode.getData() instanceof Menu) {

				transaccionMenu(treeNode, usuario);
			}

		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param data
	 */
	private void transaccionMenu(TreeNode treeNode, Usuario usuario)
			throws BaseException {

		if (treeNode.getData() != null) {
			if (treeNode.getData() instanceof Menu) {
				Menu menu = (Menu) treeNode.getData();

				if (menu.getUsuarioMenu() == null) {

					if (treeNode.isSelected()) {

						UsuarioMenu usuarioMenu = new UsuarioMenu();
						usuarioMenu.setUsuario(usuario);
						usuarioMenu.setMenu(menu);
						StringBuilder buffer = new StringBuilder();
						buffer.append(menu.isCrea() ? "C" : "");
						buffer.append(menu.isEdita() ? "E" : "");
						buffer.append(menu.isElimina() ? "B" : "");
						buffer.append(menu.isImprime() ? "I" : "");
						usuarioMenu.setAcceso(buffer.toString());
						usuarioMenuService.save(usuarioMenu);
					}
				} else {
					UsuarioMenu usuarioMenu = menu.getUsuarioMenu();
					if (treeNode.isSelected()) {

						StringBuilder buffer = new StringBuilder();
						buffer.append(menu.isCrea() ? "C" : "");
						buffer.append(menu.isEdita() ? "E" : "");
						buffer.append(menu.isElimina() ? "B" : "");
						buffer.append(menu.isImprime() ? "I" : "");
						usuarioMenu.setAcceso(buffer.toString());
						usuarioMenuService.save(usuarioMenu);
						return;
					}
					usuarioMenuService.delete(usuarioMenu);
				}
			}

		}
		if (!treeNode.getChildren().isEmpty()) {
			for (TreeNode hijo : treeNode.getChildren()) {
				transaccionMenu(hijo, usuario);
			}
		}
	}

}
