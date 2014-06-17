/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.venta.EstadoVentaItemEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.venta.item.VentaItemDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDaoImpl
 * @date 15/01/2014
 * 
 */
@Repository("ventaItemDao")
public class VentaItemDaoImpl extends GenericJpaRepository<VentaItem, Integer>
		implements Serializable, VentaItemDao {

	/**
	 * 15/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.venta.item.VentaItemDao#findVentaByFechas
	 * (java.util.Date, java.util.Date)
	 */
	@Override
	public List<VentaItem> findVentaByFechas(Date start, Date end,
			int firstResult, int maxResults) {
		Criterion criterion = Restrictions.between("fecha", start, end);
		Criterion criterion2 = Restrictions.eq("estado",
				EstadoVentaItemEnum.ACTIVO);
		return findByCriteria(firstResult, maxResults, criterion, criterion2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.venta.item.VentaItemDao#
	 * countFindVentaByFechas(java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public int countFindVentaByFechas(Date start, Date end) {
		Criterion criterion = Restrictions.between("fecha", start, end);
		Criterion criterion2 = Restrictions.eq("estado",
				EstadoVentaItemEnum.ACTIVO);
		return countByCriteria(criterion, criterion2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.venta.item.VentaItemDao#findByNumeroFactura
	 * (java.lang.String)
	 */
	@Override
	public VentaItem findByNumeroFactura(String consecutivo) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria crit = session.createCriteria(VentaItem.class);
		crit.createAlias("numeroFactura", "numeroFactura");

		crit.add(Restrictions.eq("numeroFactura.consecutivo", consecutivo));

		crit.setMaxResults(BigInteger.ONE.intValue());

		Object object = crit.uniqueResult();

		if (object != null) {
			return (VentaItem) object;
		}
		return null;
	}

}
