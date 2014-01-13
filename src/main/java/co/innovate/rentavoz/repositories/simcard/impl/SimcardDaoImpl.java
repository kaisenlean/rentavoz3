/**
 * 
 */
package co.innovate.rentavoz.repositories.simcard.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.EstadosSimcardEnum;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.simcard.SimcardDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SimcardDaoImpl
 * @date 13/01/2014
 * 
 */
@Repository("simcardDao")
public class SimcardDaoImpl extends GenericJpaRepository<Simcard, Integer>
		implements Serializable, SimcardDao {

	/**
	 * 13/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ZERO
	 */
	private static final int ZERO = 0;
	/**
	 * 13/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.simcard.SimcardDao#findDisponibles(java.lang.String)
	 */
	@Override
	public List<Simcard> findDisponibles(String criteria) {
		Criterion criterion = Restrictions
				.conjunction()
				.add(Restrictions.eq("simEstado", EstadosSimcardEnum.EN_BLANCO))
				.add(Restrictions.like("simIccid", criteria));
		return findByCriteria(criterion);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.simcard.SimcardDao#findByScId(java.lang.String)
	 */
	@Override
	public Simcard findByScId(String simIccid) throws BaseException {

		Criterion criterion = Restrictions.eq("simIccid", simIccid);

		List<Simcard> lista = findByCriteria(criterion);

		if (lista.isEmpty()) {
			return null;
		}

		return lista.get(ZERO);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.simcard.SimcardDao#getByScId(java.lang.String)
	 */
	@Override
	public List<Simcard> getByScId(String simIccid) throws BaseException {
		Criterion criterion = Restrictions
				.conjunction()
				.add(Restrictions.eq("simEstado", EstadosSimcardEnum.EN_BLANCO))
				.add(Restrictions.like("simIccid", simIccid));
		return findByCriteria(criterion);
	}

}
