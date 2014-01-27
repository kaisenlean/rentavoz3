/**
 * 
 */
package co.innovate.rentavoz.repositories.cron.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.cron.CronActivity;
import co.innovate.rentavoz.repositories.cron.CronActivityDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CronActivityDaoImpl
 * @date 27/01/2014
 *
 */
@Repository("cronActivityDao")
public class CronActivityDaoImpl extends GenericJpaRepository<CronActivity, String> implements Serializable,CronActivityDao {

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
