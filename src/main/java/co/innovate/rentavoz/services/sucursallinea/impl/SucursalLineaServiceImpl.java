/**
 * 
 */
package co.innovate.rentavoz.services.sucursallinea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.SucursalLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.sucursallinea.SucursalLineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.sucursallinea.SucursalLineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalLineaServiceImpl
 * @date 13/01/2014
 *
 */
@Service("sucursalLineaService")
public class SucursalLineaServiceImpl extends GenericServiceImpl<SucursalLinea, Integer> implements Serializable, SucursalLineaService {

	@Autowired
	private SucursalLineaDao sucursalLineaDao;
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<SucursalLinea, Integer> getDao() {
		return sucursalLineaDao;
	}

}
