/**
 * 
 */
package co.innovate.rentavoz.services.opcion.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.opcion.OpcionDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.opcion.OpcionService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionServiceImpl
 * @date 18/01/2014
 *
 */
@Service("opcionService")
public class OpcionServiceImpl extends GenericServiceImpl<Opcion,String> implements OpcionService,Serializable{

	
	@Autowired
	private OpcionDao opcionDao;
	/**
	 * 18/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Opcion, String> getDao() {
		return opcionDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.opcion.OpcionService#findByClave(java.lang.String)
	 */
	@Override
	public Opcion findByClave(String clave) throws BaseException {
		return opcionDao.findByClave(clave);
	}

}
