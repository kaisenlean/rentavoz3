/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.detalle.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.venta.item.detalle.VentaItemDetalleItemDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDetalleItemDaoImpl
 * @date 17/02/2014
 *
 */
@Repository("ventaItemDetalleItemDao")
public class VentaItemDetalleItemDaoImpl extends GenericJpaRepository<VentaItemDetalleItem, Integer> implements VentaItemDetalleItemDao,Serializable{

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ID_VENTA
	 */
	private static final String ID_VENTA = "idVenta";
	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.venta.item.detalle.VentaItemDetalleItemDao#findByVentaItem(co.innovate.rentavoz.model.venta.VentaItem)
	 */
	@Override
	public List<VentaItemDetalleItem> findByVentaItem(VentaItem ventaItem) {
		Criterion criterion = Restrictions.eq(ID_VENTA, ventaItem);
		return findByCriteria(criterion);
	}

}
