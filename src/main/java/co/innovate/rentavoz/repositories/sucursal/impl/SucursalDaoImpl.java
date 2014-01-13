/**
 * 
 */
package co.innovate.rentavoz.repositories.sucursal.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.sucursal.SucursalDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("sucursalDao")
public class SucursalDaoImpl extends GenericJpaRepository<Sucursal, Integer> implements SucursalDao,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
