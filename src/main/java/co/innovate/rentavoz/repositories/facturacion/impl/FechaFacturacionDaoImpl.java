/**
 * 
 */
package co.innovate.rentavoz.repositories.facturacion.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacionDaoImpl
 * @date 26/01/2014
 *
 */
@Repository("FechaFacturacionDao")
public class FechaFacturacionDaoImpl extends GenericJpaRepository<FechaFacturacion, Integer> implements Serializable ,FechaFacturacionDao{

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
