/**
 * 
 */
package co.innovate.rentavoz.services.simcard.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.simcard.SimcardDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.simcard.SimcardService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SimcardServiceImpl
 * @date 13/01/2014
 *
 */
@Service("simcardService")
public class SimcardServiceImpl extends GenericServiceImpl<Simcard, Integer> implements Serializable,SimcardService {

	@Autowired
	private SimcardDao simcardDao;
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.simcard.SimcardService#findDisponibles(java.lang.String)
	 */
	@Override
	public List<Simcard> findDisponibles(String criteria) {
		return simcardDao.findDisponibles(criteria);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.simcard.SimcardService#findByScId(java.lang.String)
	 */
	@Override
	public Simcard findByScId(String simIccid) throws BaseException {
		return simcardDao.findByScId(simIccid);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.simcard.SimcardService#getByScId(java.lang.String)
	 */
	@Override
	public List<Simcard> getByScId(String simIccid) throws BaseException {
		return simcardDao.getByScId(simIccid);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Simcard, Integer> getDao() {
		return simcardDao;
	}

}
