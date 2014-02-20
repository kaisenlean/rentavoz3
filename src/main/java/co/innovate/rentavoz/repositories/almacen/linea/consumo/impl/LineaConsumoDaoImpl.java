/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.linea.consumo.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoDaoImpl
 * @date 19/02/2014
 *
 */
@Repository("lineaConsumoDao")
public class LineaConsumoDaoImpl extends GenericJpaRepository<LineaConsumo, Integer> implements Serializable,LineaConsumoDao{

	/**
	 * 20/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FECHA
	 */
	private static final String FECHA = "fecha";
	/**
	 * 20/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * LINEA2
	 */
	private static final String LINEA2 = "linea";
	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao#findByLinea(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LineaConsumo> findByLinea(Linea linea) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		Criterion criterion = Restrictions.eq(LINEA2, linea);
		crit.addOrder(Order.desc(FECHA));
		crit.add(criterion);
		return crit.list();
	}

}
