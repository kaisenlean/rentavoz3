/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaDaoImpl
 * @date 5/02/2014
 *
 */
@Repository("ventaLineaDao")
public class VentaLineaDaoImpl extends GenericJpaRepository<VentaLinea, Integer> implements Serializable,VentaLineaDao {

	/**
	 * 5/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#findByVenta(co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@Override
	public List<VentaLinea> findByVenta(Venta venta) {
		Criterion criterion=Restrictions.eq("ventaidVenta", venta);
		return findByCriteria(criterion);
	}

}
