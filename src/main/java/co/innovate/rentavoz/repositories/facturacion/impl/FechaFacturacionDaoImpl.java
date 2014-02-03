/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacionDaoImpl
 * @date 26/01/2014
 *
 */
@Repository("FechaFacturacionDao")
public class FechaFacturacionDaoImpl extends GenericJpaRepository<FechaFacturacion, Integer> implements Serializable ,FechaFacturacionDao{

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao#findActivas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FechaFacturacion> findActivas() {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria crit = session.createCriteria(getEntityClass());
		crit.add(Restrictions.eq("activo", Boolean.TRUE));
		crit.addOrder(Order.asc("fechaInicio"));
		return crit.list();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao#findByFecha(java.util.Date)
	 */
	@Override
	public FechaFacturacion findByFecha(Date fecha) {
		Query query= getEntityManager().createQuery("SELECT f FROM FechaFacturacion f WHERE  :fecha >=f.fechaInicio AND :fecha < f.fechaFin ");
		query.setParameter("fecha", fecha);
		query.setMaxResults(1);
		return (FechaFacturacion) (query.getResultList().isEmpty()?null:query.getSingleResult());
	}

}
