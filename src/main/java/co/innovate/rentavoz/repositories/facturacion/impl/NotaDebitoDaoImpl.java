/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.facturacion.NotaDebito;
import co.innovate.rentavoz.repositories.facturacion.NotaDebitoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoDaoImpl
 * @date 11/03/2014
 * 
 */
@Repository("notaDebitoDao")
public class NotaDebitoDaoImpl extends
		GenericJpaRepository<NotaDebito, Integer> implements NotaDebitoDao,
		Serializable {

	/**
	 * 14/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FECHA_EMISION
	 */
	private static final String FECHA_EMISION = "fechaEmision";
	/**
	 * 12/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SUCURSAL2
	 */
	private static final String SUCURSAL2 = "sucursal";
	/**
	 * 12/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * CREADOR
	 */
	private static final String CREADOR = "creador";
	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findByCriteria
	 * (java.lang.String, int, int, org.hibernate.criterion.Order)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NotaDebito> findByCriteria(String query, int firstResult,
			int maxResults, Order order) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(NotaDebito.class);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);

		criteria.createAlias("cliente", "cliente");
		criteria.createAlias(CREADOR, CREADOR);
		Criterion criterion = Restrictions
				.disjunction()

				.add(Restrictions.like("cliente.terNombre", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("cliente.terApellidos", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("creador.terNombre", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("creador.terApellidos", query,
						MatchMode.ANYWHERE));

		criteria.add(criterion);
		criteria.addOrder(order);

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#countByCriteria
	 * (java.lang.String)
	 */
	@Override
	public int countByCriteria(String query) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(NotaDebito.class);

		criteria.createAlias("cliente", "cliente");
		criteria.createAlias(CREADOR, CREADOR);
		Criterion criterion = Restrictions
				.disjunction()

				.add(Restrictions.like("cliente.terNombre", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("cliente.terApellidos", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("creador.terNombre", query,
						MatchMode.ANYWHERE))
				.add(Restrictions.like("creador.terApellidos", query,
						MatchMode.ANYWHERE));

		criteria.add(criterion);

		criteria.setProjection(Projections.rowCount());
		return Long.valueOf(criteria.list().get(0).toString()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findByFecha
	 * (java.util.Date)
	 */
	@Override
	public List<NotaDebito> findByFecha(Date fecha) {
		Criterion criterion = Restrictions.eq(FECHA_EMISION, fecha);
		return findByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<NotaDebito> findByGenerador(Tercero tercero) {
		Criterion criterion = Restrictions.eq(CREADOR,tercero);
		return findByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#sumByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public double sumByGenerador(Tercero tercero) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(NotaDebito.class);

		Criterion criterion = Restrictions.eq(CREADOR,tercero);
		criteria.add(criterion);
		criteria.setProjection(Projections.sum("valor"));
		return Double.valueOf(criteria.list().get(0)==null?BigInteger.ZERO.toString():criteria.list().get(0).toString());
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public List<NotaDebito> findBySucursal(Sucursal sucursal) {
		Criterion criterion = Restrictions.eq(SUCURSAL2,sucursal);
		return findByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#sumBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(NotaDebito.class);

		Criterion criterion = Restrictions.eq(SUCURSAL2,sucursal);
		criteria.add(criterion);
		criteria.setProjection(Projections.sum("valor"));
		if (criteria.list().isEmpty()) {
			return BigInteger.ZERO.doubleValue();
		}
		return Double.valueOf(criteria.list().get(0)==null?BigInteger.ZERO.toString():criteria.list().get(0).toString());
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public List<NotaDebito> findByGenerador(Tercero tercero, Date fecha) {
		Criterion criterion = Restrictions.eq(CREADOR,tercero);
		Criterion criterion2= Restrictions.eq("fechaEmision", fecha);
		return findByCriteria(criterion,criterion2);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#sumByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public double sumByGenerador(Tercero tercero, Date date) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(NotaDebito.class);

		Criterion criterion = Restrictions.eq(CREADOR,tercero);
		Criterion criterion2 = Restrictions.eq(FECHA_EMISION,date);
		criteria.add(criterion);
		criteria.add(criterion2);
		criteria.setProjection(Projections.sum("valor"));
		
		return Double.valueOf(criteria.list().get(0)==null?BigInteger.ZERO.toString():criteria.list().get(0).toString());
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#findBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<NotaDebito> findBySucursal(Sucursal sucursal, Date fecha) {
		Criterion criterion = Restrictions.eq(SUCURSAL2,sucursal);
		Criterion criterion2 = Restrictions.eq(FECHA_EMISION, fecha);
		return findByCriteria(criterion,criterion2);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao#sumBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal, Date fecha) {
		Session session = getEntityManager().unwrap(Session.class);

		
		
		
		Criteria criteria = session.createCriteria(NotaDebito.class);

		Criterion criterion = Restrictions.eq(SUCURSAL2,sucursal);
		Criterion criterion2 = Restrictions.eq(FECHA_EMISION, fecha);
		criteria.add(criterion);
		criteria.add(criterion2);
		criteria.setProjection(Projections.sum("valor"));
		if (criteria.list().isEmpty()) {
			return BigInteger.ZERO.doubleValue();
		}
		return Double.valueOf(criteria.list().get(0)==null?BigInteger.ZERO.toString():criteria.list().get(0).toString());
	}

}
