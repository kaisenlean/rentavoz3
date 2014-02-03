/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
public class VentaItemDaoImpl extends GenericJpaRepository<VentaItem, Integer> implements Serializable,VentaItemDao
{

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.venta.item.VentaItemDao#findVentaByFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public List<VentaItem> findVentaByFechas(Date start, Date end,int firstResult,int maxResults) {
		Criterion criterion = Restrictions.between("fecha", start, end);
		return findByCriteria(firstResult,maxResults,criterion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.venta.item.VentaItemDao#countFindVentaByFechas(java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public int countFindVentaByFechas(Date start, Date end) {
		Criterion criterion = Restrictions.between("fecha", start, end);
		return countByCriteria(criterion);
	}

}
