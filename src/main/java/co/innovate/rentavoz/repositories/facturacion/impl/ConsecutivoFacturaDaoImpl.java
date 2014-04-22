/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.facturacion.ConsecutivoFactura;
import co.innovate.rentavoz.repositories.facturacion.ConsecutivoFacturaDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsecutivoFacturaDaoImpl
 * @date 21/04/2014
 *
 */
@Repository("consecutivoFacturaDao")
public class ConsecutivoFacturaDaoImpl extends GenericJpaRepository<ConsecutivoFactura, Integer> implements ConsecutivoFacturaDao, Serializable {

	/**
	 * 21/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
