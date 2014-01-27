/**
 * 
 */
package co.innovate.rentavoz.services.cron.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.cron.CronActivity;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.cron.CronActivityDao;
import co.innovate.rentavoz.services.cron.CronActivityService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CronActivityServiceImpl
 * @date 27/01/2014
 *
 */
@Service("cronActivityService")
public class CronActivityServiceImpl extends GenericServiceImpl<CronActivity, String> implements Serializable,CronActivityService{

	
	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CronActivityDao cronActivityDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<CronActivity, String> getDao() {
		return cronActivityDao;
	}
}
