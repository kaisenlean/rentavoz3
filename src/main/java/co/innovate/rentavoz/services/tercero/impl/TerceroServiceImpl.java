/**
 * 
 */
package co.innovate.rentavoz.services.tercero.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.tercero.TerceroDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.tercero.TerceroService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class TerceroServiceImpl
 * @date 12/01/2014
 *
 */
@Service("terceroService")
public class TerceroServiceImpl extends GenericServiceImpl<Tercero, Integer> implements TerceroService {

	
	@Autowired
	private TerceroDao terceroDao;
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tercero.TerceroService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Tercero> findByCriterio(String criterio) {
		return terceroDao.findByCriterio(criterio);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tercero.TerceroService#findByDocumento(java.lang.String)
	 */
	@Override
	public Tercero findByDocumento(String val) {
		return terceroDao.findByDocumento(val);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tercero.TerceroService#findByUsuario(co.innovate.rentavoz.model.profile.Usuario)
	 */
	@Override
	public Tercero findByUsuario(Usuario user) {
		return terceroDao.findByUsuario(user);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tercero.TerceroService#findByCentrope(co.innovate.rentavoz.model.Centrope)
	 */
	@Override
	public List<Tercero> findByCentrope(Centrope idCentrope) {
		return terceroDao.findByCentrope(idCentrope);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.tercero.TerceroService#findByCriterioProveedor(java.lang.String)
	 */
	@Override
	public List<Tercero> findByCriterioProveedor(String criterio) {
		return findByCriterioProveedor(criterio);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Tercero, Integer> getDao() {
		return terceroDao;
	}

}
