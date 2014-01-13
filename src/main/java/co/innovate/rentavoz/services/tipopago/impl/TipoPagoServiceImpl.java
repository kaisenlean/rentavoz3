/**
 * 
 */
package co.innovate.rentavoz.services.tipopago.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.TipoPago;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.tipopago.TipoPagoDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.tipopago.TipoPagoService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoPagoServiceImpl
 * @date 13/01/2014
 *
 */
@Service("tipoPagoService")
public class TipoPagoServiceImpl extends GenericServiceImpl<TipoPago, Integer> implements TipoPagoService,Serializable{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoPagoDao tipoPagoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<TipoPago, Integer> getDao() {
		return tipoPagoDao;
	}
}
