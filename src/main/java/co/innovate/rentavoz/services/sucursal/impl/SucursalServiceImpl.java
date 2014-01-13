/**
 * 
 */
package co.innovate.rentavoz.services.sucursal.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.sucursal.SucursalDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.sucursal.SucursalService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalServiceImpl
 * @date 13/01/2014
 *
 */
@Service("sucursalService")
public class SucursalServiceImpl extends GenericServiceImpl<Sucursal, Integer> implements SucursalService,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SucursalDao sucursalDao;
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Sucursal, Integer> getDao() {
		return sucursalDao;
	}

}
