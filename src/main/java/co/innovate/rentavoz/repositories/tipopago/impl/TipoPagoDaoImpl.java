/**
 * 
 */
package co.innovate.rentavoz.repositories.tipopago.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.TipoPago;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.tipopago.TipoPagoDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoPagoDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("tipoPagoDao")
public class TipoPagoDaoImpl extends GenericJpaRepository<TipoPago, Integer> implements Serializable,TipoPagoDao{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2828714980496967965L;

}
