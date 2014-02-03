/**
 * 
 */
package co.innovate.rentavoz.services.sucursaltercero.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.sucursaltercero.SucursalTerceroDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalTerceroServiceImpl
 * @date 2/02/2014
 *
 */
@Service("sucursalTerceroService")
public class SucursalTerceroServiceImpl extends GenericServiceImpl<SucursalTercero, Integer> implements Serializable, SucursalTerceroService {

	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SucursalTerceroDao sucursalTerceroDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<SucursalTercero, Integer> getDao() {
		return sucursalTerceroDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService#findByTercero(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<SucursalTercero> findByTercero(Tercero tercero) {
		return sucursalTerceroDao.findByTercero(tercero);
	}
}
