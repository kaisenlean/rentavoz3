/**
 * 
 */
package co.innovate.rentavoz.repositories.formato.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.formato.Formato;
import co.innovate.rentavoz.repositories.formato.FormatoDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FormatoDaoImpl
 * @date 19/02/2014
 *
 */
@Repository("formatoDao")
public class FormatoDaoImpl extends GenericJpaRepository<Formato, Integer> implements FormatoDao , Serializable {

	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
