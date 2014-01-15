/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegaingreso.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao;
import co.innovate.rentavoz.repositories.bodegaingreso.BodegaIngresoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaIngresoDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("bodegaIngresoDao")
public class BodegaIngresoDaoImpl extends GenericJpaRepository<BodegaIngreso, Integer> implements Serializable,BodegaIngresoDao{

	@Autowired
	private BodegaExistenciaDao bodegaExistenciaDao;
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegaingreso.BodegaIngresoDao#findByFechas(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BodegaExistencia> findByFechas(Date start, Date end) {
		List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();
		Query query = getEntityManager()
				.createQuery(
						"SELECT i FROM BodegaIngreso i WHERE i.fechaIngreso BETWEEN :start AND :end");
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		List<BodegaIngreso> ingresos = query.getResultList();
		
		for (BodegaIngreso bodegaIngreso : ingresos) {
			existencias.addAll(bodegaExistenciaDao.findByIngreso(bodegaIngreso));
		}
		return existencias;
	}

}
