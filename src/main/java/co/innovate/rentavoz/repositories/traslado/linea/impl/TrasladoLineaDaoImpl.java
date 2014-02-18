/**
 * 
 */
package co.innovate.rentavoz.repositories.traslado.linea.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.traslado.linea.TrasladoLineaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaDaoImpl
 * @date 17/02/2014
 *
 */
@Repository("trasladoLineaDao")
public class TrasladoLineaDaoImpl extends GenericJpaRepository<TrasladoLinea, Integer>implements Serializable,TrasladoLineaDao {

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
