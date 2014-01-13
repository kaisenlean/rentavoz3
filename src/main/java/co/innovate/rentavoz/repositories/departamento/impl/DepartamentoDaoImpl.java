/**
 * 
 */
package co.innovate.rentavoz.repositories.departamento.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.departamento.DepartamentoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class DepartamentoDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("departamentoDao")
public class DepartamentoDaoImpl extends GenericJpaRepository<Departamento, Integer> implements DepartamentoDao,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.departamento.DepartamentoDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Departamento> findByCriterio(String criterio) {
		
		Criterion criterion = Restrictions.like("depNombre", criterio,MatchMode.ANYWHERE);

		return findByCriteria(criterion);
	}

}
