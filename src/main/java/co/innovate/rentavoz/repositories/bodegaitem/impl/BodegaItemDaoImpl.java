/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaitem.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.repositories.bodegaitem.BodegaItemDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaItemDaoImpl
 * @date 13/01/2014
 * 
 */
@Repository("bodegaItemDao")
public class BodegaItemDaoImpl extends
		GenericJpaRepository<BodegaItem, Integer> implements Serializable,
		BodegaItemDao {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REFERENCIA2
	 */
	private static final String REFERENCIA2 = "referencia";
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NOMBRE2
	 */
	private static final String NOMBRE2 = "nombre";
	/**
	 * 13/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REFERENCIA
	 */
	private static final String REFERENCIA = REFERENCIA2;
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
	 * @see co.innovate.rentavoz.repositories.bodegaitem.BodegaItemDao#existReferencia(java.lang.String)
	 */
	@Override
	public boolean existReferencia(String ref) {
		Criterion criterion = Restrictions.eq(REFERENCIA, ref);
		List<BodegaItem> lista = findByCriteria(criterion);
		if (lista.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.bodegaitem.BodegaItemDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<BodegaItem> findByCriterio(String param) {
		Criterion criterion = Restrictions
				.disjunction()
				.add(Restrictions.like(NOMBRE2, param, MatchMode.ANYWHERE))
				.add(Restrictions.like(REFERENCIA2, param, MatchMode.ANYWHERE));

		return findByCriteria(criterion);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.bodegaitem.BodegaItemDao#findByNombre(java.lang.String)
	 */
	@Override
	public BodegaItem findByNombre(String nombre) {
		Criterion criterion = Restrictions.eq(NOMBRE2, nombre);
		List<BodegaItem> lista= findByCriteria(BigInteger.ZERO.intValue(), BigInteger.ONE.intValue(), criterion);
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(BigInteger.ZERO.intValue());
	}

}
