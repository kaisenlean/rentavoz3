/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.almacen.CuotaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuotaDaoImpl
 * @date 7/02/2014
 *
 */
@Repository("cuotaDao")
public class CuotaDaoImpl extends GenericJpaRepository<Cuota, Integer> implements Serializable,CuotaDao{

	/**
	 * 7/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.CuotaDao#buscarCuotasPendientesPorCliente(co.innovate.rentavoz.model.Tercero)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cuota> buscarCuotasPendientesPorCliente(Tercero cliente) {
		Query query= getEntityManager().createQuery("SELECT c FROM Cuota c WHERE c.venta.tercero = :cliente AND c.estadoCuota = :estado");
		query.setParameter("cliente", cliente);
		query.setParameter("estado", EstadoCuotaEnum.PENDIENTE);
		
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.CuotaDao#buscarRutaDeCuotasPorCobrador(co.innovate.rentavoz.model.Tercero)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cuota> buscarRutaDeCuotasPorCobrador(Tercero cobrador) {
		Query query= getEntityManager().createQuery("SELECT c FROM Cuota c WHERE c.venta.cobrador = :cobrador AND c.estadoCuota = :estado");
		query.setParameter("cobrador", cobrador);
		query.setParameter("estado", EstadoCuotaEnum.PENDIENTE);
		
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.CuotaDao#findByVenta(co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cuota> findByVenta(Venta venta) {
		Query query= getEntityManager().createQuery("SELECT c FROM Cuota c WHERE c.venta = :venta");
		query.setParameter("venta", venta);
		return query.getResultList();
	}

}
