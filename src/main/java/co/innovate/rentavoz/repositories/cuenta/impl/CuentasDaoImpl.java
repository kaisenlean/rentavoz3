/**
 * 
 */
package co.innovate.rentavoz.repositories.cuenta.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.repositories.cuenta.CuentasDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuentasDaoImpl
 * @date 15/01/2014
 *
 */
@Repository("cuentasDao")
public class CuentasDaoImpl extends GenericJpaRepository<Cuentas, Integer> implements CuentasDao,Serializable {

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
