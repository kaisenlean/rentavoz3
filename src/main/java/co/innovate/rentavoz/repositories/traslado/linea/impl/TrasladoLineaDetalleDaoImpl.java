/**
 * 
 */
package co.innovate.rentavoz.repositories.traslado.linea.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLineaDetalle;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.traslado.linea.TrasladoLineaDetalleDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaDetalleDaoImpl
 * @date 17/02/2014
 *
 */
@Repository("trasladoLineaDetalleDao")
public class TrasladoLineaDetalleDaoImpl  extends GenericJpaRepository<TrasladoLineaDetalle, Integer> implements Serializable, TrasladoLineaDetalleDao{

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TRASLADO
	 */
	private static final String TRASLADO = "traslado";
	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.traslado.linea.TrasladoLineaDetalleDao#findByTraslado(co.innovate.rentavoz.model.traslado.linea.TrasladoLinea)
	 */
	@Override
	public List<TrasladoLineaDetalle> findByTraslado(TrasladoLinea trasladoLinea) {
		Criterion criterion = Restrictions.eq(TRASLADO, trasladoLinea);
		return findByCriteria(criterion);
				
	}

}
