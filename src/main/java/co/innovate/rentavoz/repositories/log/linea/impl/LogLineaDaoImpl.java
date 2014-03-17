/**
 * 
 */
package co.innovate.rentavoz.repositories.log.linea.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.log.linea.LogLineaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LogLineaDaoImpl
 * @date 16/03/2014
 * 
 */
@Repository("logLineaDao")
public class LogLineaDaoImpl extends GenericJpaRepository<LogLinea, Integer>
		implements LogLineaDao, Serializable {

	/**
	 * 16/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.log.linea.LogLineaDao#findByCriterio
	 * (java.util.Date, java.util.Date, java.lang.String,
	 * co.innovate.rentavoz.model.Tercero,
	 * co.innovate.rentavoz.model.log.linea.AccionLineaEnum, int, int,
	 * co.innovate.rentavoz.repositories.log.linea.Order)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogLinea> findByCriterio(Date start, Date end,
			String numeroLinea, Tercero creador,
			AccionLineaEnum accionLineaEnum, int maxRegistros,
			int registroInicial, Order order) {
		StringBuilder sql = new StringBuilder("SELECT l FROM LogLinea l WHERE ");
		Object nul = null;
		boolean fechas=false;
		boolean accion=false;
		boolean cread=false;
		sql.append("l.linea.linNumero LIKE :linea ");

		sql.append(" OR ");

		if (start != null && end != null) {
			fechas=true;
			sql.append("l.fecha BETWEEN :start AND :end");

		} else {
			sql.append("l.fecha != ").append(nul);

		}
		sql.append(" OR ");

		if (creador != null) {
			sql.append("l.usuarioCrea = :creador");
			cread=true;
		} else {
			sql.append("l.usuarioCrea != ").append(nul);
		}
		
		if (accionLineaEnum!=null) {
		accion=true;
			sql.append(" AND ");
			sql.append(" l.accion = :accion");
			
		}
		


		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("linea", "%"+numeroLinea+"%");
		if (fechas) {
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		if (accion) {
			query.setParameter("accion", accionLineaEnum);
		}
		if (cread) {
			query.setParameter("cread", creador);
		}
		query.setFirstResult(registroInicial);
		query.setMaxResults(maxRegistros);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.log.linea.LogLineaDao#countByCriterio(java.util.Date, java.util.Date, java.lang.String, co.innovate.rentavoz.model.Tercero, co.innovate.rentavoz.model.log.linea.AccionLineaEnum, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public int countByCriterio(Date start, Date end, String numeroLinea,
			Tercero creador, AccionLineaEnum accionLineaEnum) {

		StringBuilder sql = new StringBuilder("SELECT COUNT(l) FROM LogLinea l WHERE ");
		Object nul = null;
		boolean fechas=false;
		boolean accion=false;
		boolean cread=false;
		sql.append("l.linea.linNumero LIKE :linea ");

		sql.append(" OR ");

		if (start != null && end != null) {
			fechas=true;
			sql.append("l.fecha BETWEEN :start AND :end");

		} else {
			sql.append("l.fecha != ").append(nul);

		}
		sql.append(" OR ");

		if (creador != null) {
			sql.append("l.usuarioCrea = :creador");
			cread=true;
		} else {
			sql.append("l.usuarioCrea != ").append(nul);
		}
		
		if (accionLineaEnum!=null) {
		accion=true;
			sql.append(" AND ");
			sql.append(" l.accion = :accion");
			
		}
		


		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("linea", "%"+numeroLinea+"%");
		if (fechas) {
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		if (accion) {
			query.setParameter("accion", accionLineaEnum);
		}
		if (cread) {
			query.setParameter("cread", creador);
		}
		
		return Integer.valueOf(query.getSingleResult().toString());
	}

}
