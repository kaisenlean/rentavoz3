/**
 * 
 */
package co.innovate.rentavoz.repositories.plan.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.plan.PlanDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("planDao")
public class PlanDaoImpl extends GenericJpaRepository<Plan, Integer> implements PlanDao,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * PLA_NOMBRE_FIELD
	 */
	private static final String PLA_NOMBRE_FIELD = "plaNombre";
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.plan.PlanDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Plan> findByCriterio(String criterio) {
		Criterion criterion= Restrictions.like(PLA_NOMBRE_FIELD, criterio,MatchMode.ANYWHERE);
		return findByCriteria(criterion);
	}
	
	
	

}
