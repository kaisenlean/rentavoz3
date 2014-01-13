/**
 * 
 */
package co.innovate.rentavoz.repositories.sucursallinea.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.SucursalLinea;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.sucursallinea.SucursalLineaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalLineaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("sucursalLineaDao")
public class SucursalLineaDaoImpl extends GenericJpaRepository<SucursalLinea, Integer> implements SucursalLineaDao,Serializable{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
