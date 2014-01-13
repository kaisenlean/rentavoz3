package co.innovate.rentavoz.repositories.usuario.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.usuario.UsuarioDao;

@Repository(UsuarioDaoImpl.NAME_DAO)
public class UsuarioDaoImpl extends GenericJpaRepository<Usuario, String> implements UsuarioDao{

	static final String NAME_DAO = "usuarioDao";

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.usuario.UsuarioDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario login(String login, String contrasena) throws BaseException {
		Criterion criteria = Restrictions.conjunction().add(Restrictions.eq("usuario",login)).add( Restrictions.eq("contrasena",contrasena));
		
		
		List<Usuario> usuarios= findByCriteria(criteria);
		
		if (usuarios.isEmpty()) {
			return null;
		}
		
		return usuarios.get(0);
	}

}
