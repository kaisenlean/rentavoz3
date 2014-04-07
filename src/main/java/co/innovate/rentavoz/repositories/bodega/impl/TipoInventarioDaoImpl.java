/**
 * 
 */
package co.innovate.rentavoz.repositories.bodega.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.repositories.bodega.TipoInventarioDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventarioDaoImpl
 * @date 7/04/2014
 *
 */
@Repository("tipoInventarioDao")
public class TipoInventarioDaoImpl extends GenericJpaRepository<TipoInventario, Integer> implements TipoInventarioDao, Serializable {

	/**
	 * 7/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
