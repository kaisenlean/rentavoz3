/**
 * 
 */
package co.innovate.rentavoz.services.sucursalsimcard.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.SucursalSimcard;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.sucursalsimcard.SucursalSimcardDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.sucursalsimcard.SucursalSimcardService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalSimcardServiceImpl
 * @date 13/01/2014
 *
 */
@Service("sucursalSimcardService")
public class SucursalSimcardServiceImpl  extends GenericServiceImpl<SucursalSimcard, Integer> implements SucursalSimcardService,Serializable{

	
	@Autowired
	private SucursalSimcardDao sucursalSimcardDao;
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
	public GenericRepository<SucursalSimcard, Integer> getDao() {
		return sucursalSimcardDao;
	}

}
