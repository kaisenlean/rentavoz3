/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.linea.consumo.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.linea.impl.LineaDaoImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoDaoImpl
 * @date 19/02/2014
 * 
 */
@Repository("lineaConsumoDao")
public class LineaConsumoDaoImpl extends
		GenericJpaRepository<LineaConsumo, Integer> implements Serializable,
		LineaConsumoDao {

	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         PERIODO
	 */
	private static final String PERIODO = "periodo";
	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ESTADO_LINEA_ID_ESTADO_LINEA
	 */
	private static final String ESTADO_LINEA_ID_ESTADO_LINEA = "estadoLinea.idEstadoLinea";
	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ESTADO_LINEAID_ESTADO_LINEA
	 */
	private static final String ESTADO_LINEAID_ESTADO_LINEA = "estadoLineaidEstadoLinea";
	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         LIN_CORTE
	 */
	private static final String LIN_CORTE = "linCorte";
	/**
	 * 20/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         FECHA
	 */
	private static final String FECHA = "fecha";
	/**
	 * 20/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         LINEA2
	 */
	private static final String LINEA2 = "linea";
	/**
	 * 19/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao
	 * #findByLinea(co.innovate.rentavoz.model.almacen.Linea)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao
	 * #findLineasNoVendidasConConsumo
	 * (co.innovate.rentavoz.model.facturacion.FechaFacturacion)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Linea> findLineasNoVendidasConConsumo(
			FechaFacturacion periodoFacturacion, int corte) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria critLineas = session.createCriteria(Linea.class);
		critLineas.add(Restrictions.eq(LIN_CORTE, corte));
		critLineas.createAlias(ESTADO_LINEAID_ESTADO_LINEA, "estadoLinea");
		critLineas.add(Restrictions.eq(ESTADO_LINEA_ID_ESTADO_LINEA,
				LineaDaoImpl.ESTADO_LINEA_REPO));

		List<Linea> lineasCorteRepo = critLineas.list();

		Query query = getEntityManager()
				.createQuery(
						"SELECT v.lineaidLinea FROM VentaLinea v WHERE v.ventaidVenta.fechaFacturacion = :periodo");
		query.setParameter(PERIODO, periodoFacturacion);

		List<Linea> LineasFacturadas = query.getResultList();

		List<Linea> lineas = new ArrayList<Linea>();

		for (Linea linea : lineasCorteRepo) {

			if (!LineasFacturadas.contains(linea)) {

				LineaConsumo consumo = findULtimoConsumoByLinea(linea);
				if (consumo != null) {
					if (consumo.getFecha().before(
							periodoFacturacion.getFechaFin())
							&& consumo.getFecha().after(
									periodoFacturacion.getFechaInicio())) {

						lineas.add(linea);
					}
				}
			}
		}

		return lineas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao
	 * #findULtimoConsumoByLinea(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LineaConsumo findULtimoConsumoByLinea(Linea linea) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		Criterion criterion = Restrictions.eq(LINEA2, linea);
		crit.addOrder(Order.desc(FECHA));
		crit.add(criterion);
		List<LineaConsumo> consumos = crit.list();
		if (consumos.isEmpty()) {
			return null;
		}
		return consumos.get(BigInteger.ZERO.intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao
	 * #findByCriterio(java.lang.String, int, java.lang.String,
	 * java.lang.String, int, int, javax.persistence.criteria.Order)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LineaConsumo> findByCriterio(String linea, int corte,
			String maestra, String convenio, Date fecha, int firstResult,
			int maxResults, Order order)throws BaseException {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.createAlias("linea", "linea");
		crit.createAlias("linea.empresaidEmpresa", "empresa");
		Criterion criterion = Restrictions
				.conjunction()
				.add(linea != null && linea!=(StringUtils.EMPTY) ? Restrictions
						.eq("linea.linNumero", linea) : Restrictions.isNotNull("linea.linNumero"))
				.add(corte != 0 ? Restrictions.eq("linea.linCorte", corte)
						: Restrictions.isNotNull("linea.linCorte"))
				.add(convenio == null || convenio==(StringUtils.EMPTY) ? Restrictions
						.isNotNull("empresa.empNombre") : Restrictions.like(
						"empresa.empNombre", convenio,MatchMode.ANYWHERE))
				.add(Restrictions.eq("fecha", fecha));
		crit.addOrder(order);
		crit.setMaxResults(maxResults);
		crit.setFirstResult(firstResult);
		crit.add(criterion);
		return crit.list();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao#countByCriterio(java.lang.String, int, java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public int countByCriterio(String linea, int corte, String maestra,
			String convenio, Date fecha) throws BaseException {
		
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.createAlias("linea", "linea");
		crit.createAlias("linea.empresaidEmpresa", "empresa");
		Criterion criterion = Restrictions
				.conjunction()
				.add(linea != null && linea!=(StringUtils.EMPTY) ? Restrictions
						.eq("linea.linNumero", linea) : Restrictions.isNotNull("linea.linNumero"))
				.add(corte != 0 ? Restrictions.eq("linea.linCorte", corte)
						: Restrictions.isNotNull("linea.linCorte"))
				.add(convenio == null || convenio==(StringUtils.EMPTY) ? Restrictions
						.isNotNull("empresa.empNombre") : Restrictions.like(
						"empresa.empNombre", convenio,MatchMode.ANYWHERE))
				.add(Restrictions.eq("fecha", fecha));

		crit.add(criterion);
		crit.setProjection(Projections.rowCount());
		return Long.valueOf(crit.list().get(0).toString()).intValue();
	}

}
