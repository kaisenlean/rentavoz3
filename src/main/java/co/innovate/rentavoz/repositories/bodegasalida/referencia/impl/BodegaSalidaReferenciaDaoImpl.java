/**
 * 
 */
package co.innovate.rentavoz.repositories.bodegasalida.referencia.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.bodegasalida.referencia.BodegaSalidaReferenciaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaReferenciaDaoImpl
 * @date 14/01/2014
 *
 */
@Repository("bodegaSalidaReferenciaDao")
public class BodegaSalidaReferenciaDaoImpl extends GenericJpaRepository<BodegaSalidaReferencia, Integer> implements BodegaSalidaReferenciaDao {

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.bodegasalida.referencia.BodegaSalidaReferenciaDao#findByBodegaSalida(co.innovate.rentavoz.model.bodega.BodegaSalida)
	 */
	@Override
	public List<BodegaSalidaReferencia> findByBodegaSalida(
			BodegaSalida bodegaSalida) {
		Criterion criterion= Restrictions.eq("bodegaSalida", bodegaSalida);
		return findByCriteria(criterion);
	}

}
