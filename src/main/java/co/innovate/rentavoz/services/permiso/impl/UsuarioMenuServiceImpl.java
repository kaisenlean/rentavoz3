/**
 * 
 */
package co.innovate.rentavoz.services.permiso.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.model.permiso.UsuarioMenu;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.permiso.UsuarioMenuDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.permiso.UsuarioMenuService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UsuarioMenuServiceImpl
 * @date 31/01/2014
 *
 */
@Service("usuarioMenuService")
public class UsuarioMenuServiceImpl extends GenericServiceImpl<UsuarioMenu, Integer> implements UsuarioMenuService,Serializable {

	/**
	 * 31/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioMenuDao usuarioMenuDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<UsuarioMenu, Integer> getDao() {
		return usuarioMenuDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.permiso.UsuarioMenuService#findByUsuarioAndMenu(co.innovate.rentavoz.model.profile.Usuario, co.innovate.rentavoz.model.Menu)
	 */
	@Override
	public UsuarioMenu findByUsuarioAndMenu(Usuario usuario, Menu menu) {
		return usuarioMenuDao.findByUsuarioAndMenu(usuario, menu);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.permiso.UsuarioMenuService#findByUsuario(co.innovate.rentavoz.model.profile.Usuario)
	 */
	@Override
	public List<Menu> findByUsuario(Usuario user,String padre) {
		return usuarioMenuDao.findByUsuario(user,padre);
	}

}
