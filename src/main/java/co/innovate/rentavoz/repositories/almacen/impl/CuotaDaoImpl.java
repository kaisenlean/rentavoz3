/**
 * 
 */
package co.innovate.rentavoz.repositories.almacen.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.repositories.almacen.CuotaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuotaDaoImpl
 * @date 7/02/2014
 *
 */
@Repository("cuotaDao")
public class CuotaDaoImpl extends GenericJpaRepository<Cuota, Integer> implements Serializable,CuotaDao{

	/**
	 * 7/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
