/**
 * 
 */
package co.innovate.rentavoz.repositories.operador.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.operador.OperadorDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OperadorDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("operadorDao")
public class OperadorDaoImpl extends GenericJpaRepository<Operador, Integer> implements OperadorDao,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.operador.OperadorDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Operador> findByCriterio(String criterio) {
		Criterion criterion = Restrictions.like("opeNombre", criterio,MatchMode.ANYWHERE);
		return findByCriteria(criterion);
	}

}
