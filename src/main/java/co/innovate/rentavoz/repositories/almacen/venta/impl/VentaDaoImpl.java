/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta.impl;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.almacen.venta.VentaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaDaoImpl
 * @date 4/02/2014
 *
 */
@Repository("ventaDao")
public class VentaDaoImpl extends GenericJpaRepository<Venta, Integer> implements Serializable ,VentaDao{

	/**
	 * 5/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaDao#findByConsecutivo(java.lang.String)
	 */
	@Override
	public Venta findByConsecutivo(String consecutivo) {
	 
		Query query = getEntityManager().createQuery("SELECT v FROM Venta v WHERE v.numeroFactura.consecutivo = :consecutivo");
		query.setMaxResults(BigInteger.ONE.intValue());
		query.setParameter("consecutivo", consecutivo);
		Object object = query.getSingleResult();
		
		if (object==null) {
			return null;
		}
		return (Venta) object;
	}

}
