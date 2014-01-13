package co.innovate.rentavoz.services.usuario.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.usuario.UsuarioDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.usuario.UsuarioService;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz-3
* @class UsuarioServiceImpl
* @date 12/01/2014
*
*/
@Service(UsuarioServiceImpl.NAME_SERVICE)
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, String> implements UsuarioService {

	static final String NAME_SERVICE = "usuarioService";
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public GenericRepository<Usuario, String> getDao() {
		return usuarioDao;
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.usuario.UsuarioService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario login(String login, String contrasena) throws BaseException {
		return usuarioDao.login(login, contrasena);
	}

}
