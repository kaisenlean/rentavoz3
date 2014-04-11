/**
 * 
 */
package co.innovate.rentavoz.repositories.planpago.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.planpago.PlanPagoDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanPagoRepositoryImpl
 * @date 10/04/2014
 *
 */
@Repository("planPagoDao")
public class PlanPagoDaoImpl extends GenericJpaRepository<PlanPago, Integer> implements PlanPagoDao , Serializable {

	/**
	 * 10/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
