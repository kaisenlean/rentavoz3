/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.venta.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.EstadoVentaEnum;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.EstadoDevolucionEnum;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
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
public class VentaLineaDaoImpl extends
		GenericJpaRepository<VentaLinea, Integer> implements Serializable,
		VentaLineaDao {

	/**
	 * 5/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#findByVenta
	 * (co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@Override
	public List<VentaLinea> findByVenta(Venta venta) {
		Criterion criterion = Restrictions.eq("ventaidVenta", venta);
		return findByCriteria(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#
	 * findHistorialFacturacion(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VentaLinea> findHistorialFacturacion(Linea linea) {

		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.createAlias("ventaidVenta", "ventaidVenta");
		Criterion criterion = Restrictions
				.conjunction()
				.add(Restrictions.eq("lineaidLinea", linea))
				.add(Restrictions.eq("ventaidVenta.estadoVenta",
						EstadoVentaEnum.ACTIVA));
		crit.add(criterion);
		crit.addOrder(Order.asc("ventaidVenta.fechaFacturacion"));

		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#
	 * findLineasConDevolucionByCliente(co.innovate.rentavoz.model.Tercero)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VentaLinea> findLineasConDevolucionByCliente(Tercero tercero) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT v FROM VentaLinea v WHERE v.estadoDevolucion = :estadoDevolucion AND  v.ventaidVenta.estadoVenta = :estadoVenta  AND :now BETWEEN v.ventaidVenta.fechaFacturacion.fechaInicio AND v.ventaidVenta.fechaFacturacion.fechaFin");
		
		query.setParameter("estadoDevolucion", EstadoDevolucionEnum.PENDIENTE);
		query.setParameter("estadoVenta", EstadoVentaEnum.ACTIVA);
		query.setParameter("now", Calendar.getInstance().getTime());
		
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#findVentaSinAjuste(co.innovate.rentavoz.model.facturacion.FechaFacturacion)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> findVentaAjuste(FechaFacturacion fechaFacturacion,Boolean ajuste) {
		Query query= getEntityManager().createQuery("SELECT DISTINCT v.ventaidVenta FROM VentaLinea v WHERE v.lineaidLinea.ajuste = :ajuste AND v.ventaidVenta.fechaFacturacion = :fechaFacturacion AND v.ventaidVenta.estadoVenta = :estado");
		query.setParameter("ajuste", ajuste);
		query.setParameter("fechaFacturacion", fechaFacturacion);
		query.setParameter("estado", EstadoVentaEnum.ACTIVA);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao#findValorVentaAjuste(co.innovate.rentavoz.model.facturacion.FechaFacturacion, java.lang.Boolean)
	 */
	@Override
	public Double findValorVentaAjuste(FechaFacturacion fechaFacturacion,
			Boolean ajuste) {
		
		Query query= getEntityManager().createQuery("SELECT DISTINCT  SUM(v.ventaidVenta.venSaldo) FROM VentaLinea v WHERE v.lineaidLinea.ajuste = :ajuste AND v.ventaidVenta.fechaFacturacion = :fechaFacturacion AND v.ventaidVenta.estadoVenta = :estado");
		query.setParameter("ajuste", ajuste);
		query.setParameter("fechaFacturacion", fechaFacturacion);
		query.setParameter("estado", EstadoVentaEnum.ACTIVA);
		
		return Double.valueOf(query.getSingleResult().toString());
	}

}
