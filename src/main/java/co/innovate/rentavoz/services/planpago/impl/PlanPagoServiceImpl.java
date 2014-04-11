/**
 * 
 */
package co.innovate.rentavoz.services.planpago.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.planpago.PlanPagoDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.planpago.PlanPagoService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanPagoServiceImpl
 * @date 10/04/2014
 *
 */
@Service("planPagoService")
public class PlanPagoServiceImpl extends GenericServiceImpl<PlanPago, Integer> implements PlanPagoService,Serializable {

	
	/**
	 * 10/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PlanPagoDao planPagoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<PlanPago, Integer> getDao() {
		return planPagoDao;
	}
	
	
}
