/**
 * 
 */
package co.innovate.rentavoz.repositories.permiso.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.model.permiso.UsuarioMenu;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.permiso.UsuarioMenuDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UsuarioMenuDaoImpl
 * @date 31/01/2014
 *
 */
@Repository("usuarioMenuDao")
public class UsuarioMenuDaoImpl extends GenericJpaRepository<UsuarioMenu, Integer> implements Serializable , UsuarioMenuDao {

	/**
	 * 31/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.permiso.UsuarioMenuDao#findByUsuarioAndMenu(co.innovate.rentavoz.model.profile.Usuario, co.innovate.rentavoz.model.Menu)
	 */
	@Override
	public UsuarioMenu findByUsuarioAndMenu(Usuario usuario, Menu menu) {
		Criterion criterion= Restrictions.conjunction().add(Restrictions.eq("usuario", usuario)).add(Restrictions.eq("menu", menu));
		List<UsuarioMenu> lista= findByCriteria(criterion);
		
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.permiso.UsuarioMenuDao#findByUsuario(co.innovate.rentavoz.model.profile.Usuario)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findByUsuario(Usuario user,String padre) {
		Query query= getEntityManager().createQuery("SELECT m.menu FROM UsuarioMenu m WHERE m.usuario = :usuario And m.menu.padre = :padre");
		query.setParameter("usuario", user);
		query.setParameter("padre", padre);
		return query.getResultList();
	}

}
