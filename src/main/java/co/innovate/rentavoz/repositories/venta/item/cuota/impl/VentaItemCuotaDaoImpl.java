/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.cuota.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.venta.item.cuota.VentaItemCuotaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemCuotaDaoImpl
 * @date 15/01/2014
 *
 */
@Repository("ventaItemCuota")
public class VentaItemCuotaDaoImpl extends GenericJpaRepository<VentaItemCuota, Integer> implements VentaItemCuotaDao,Serializable {

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.venta.item.cuota.VentaItemCuotaDao#findCuotasPendientesByCliente(co.innovate.rentavoz.model.Tercero)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VentaItemCuota> findCuotasPendientesByCliente(Tercero tercero) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria crit = session.createCriteria(VentaItemCuota.class);
		crit.createAlias("idVenta", "idVenta");
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("estado", EstadoVentaItemCuotaEnum.PENDIENTE)).add(Restrictions.eq("idVenta.cliente", tercero));
		crit.add(criterion);
		
		return crit.list();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.venta.item.cuota.VentaItemCuotaDao#findCuotasByVenta(co.innovate.rentavoz.model.venta.VentaItem)
	 */
	@Override
	public List<VentaItemCuota> findCuotasByVenta(VentaItem ventaItem) {
		Criterion criterion=Restrictions.eq("idVenta", ventaItem);
		return findByCriteria(criterion);
	}

}
