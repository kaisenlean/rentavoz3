/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegasalida.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.bodegasalida.BodegaSalidaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaDaoImpl
 * @date 14/01/2014
 *
 */
@Repository("bodegaSalidaDao")
public class BodegaSalidaDaoImpl extends GenericJpaRepository<BodegaSalida, Integer> implements Serializable,BodegaSalidaDao {

	/**
	 * 14/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegasalida.BodegaSalidaDao#findByFechas(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BodegaSalidaReferencia> findByFechas(Date start, Date end) {
		Query query = getEntityManager().createQuery("SELECT s FROM BodegaSalidaReferencia s WHERE s.bodegaSalida.fechaSalida BETWEEN :start AND :end");
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		return query.getResultList();
	}

}
