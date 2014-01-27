/**
 * 
 */
package co.innovate.rentavoz.repositories.centrope.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.repositories.centrope.CentropeDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CentropeDaoImpl
 * @date 26/01/2014
 *
 */
@Repository("centropeDao")
public class CentropeDaoImpl extends GenericJpaRepository<Centrope, Integer> implements CentropeDao,Serializable {

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.centrope.CentropeDao#findByParametro(java.lang.String)
	 */
	@Override
	public List<Centrope> findByParametro(String parametro) {
		
		Criterion criterion = Restrictions.eq("parametro", parametro);
		
		return findByCriteria(criterion);
	}

}
