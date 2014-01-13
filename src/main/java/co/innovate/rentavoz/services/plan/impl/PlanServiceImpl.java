/**
 * 
 */
package co.innovate.rentavoz.services.plan.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.plan.PlanDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.plan.PlanService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanServiceImpl
 * @date 13/01/2014
 *
 */
@Service("planService")
public class PlanServiceImpl extends GenericServiceImpl<Plan, Integer> implements PlanService,Serializable {

	
	@Autowired
	private PlanDao planDao;
	/**
	 * 
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.plan.PlanService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Plan> findByCriterio(String criterio) {
		return planDao.findByCriterio(criterio);
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Plan, Integer> getDao() {
		return planDao;
	}

}
