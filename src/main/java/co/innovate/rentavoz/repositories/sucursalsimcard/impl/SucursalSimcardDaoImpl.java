/**
 * 
 */
package co.innovate.rentavoz.repositories.sucursalsimcard.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.SucursalSimcard;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.sucursalsimcard.SucursalSimcardDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalSimcardDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("sucursalSimcardDao")
public class SucursalSimcardDaoImpl extends GenericJpaRepository<SucursalSimcard, Integer> implements SucursalSimcardDao ,Serializable{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
