/**
 * 
 */
package co.innovate.rentavoz.repositories.estadolinea.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.repositories.estadolinea.EstadoLineaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EstadoLineaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("estadoLineaDao")
public class EstadoLineaDaoImpl extends GenericJpaRepository<EstadoLinea, Integer> implements Serializable,EstadoLineaDao{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
