/**
 * 
 */
package co.innovate.rentavoz.services.facturacion.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.facturacion.ConsecutivoFactura;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.facturacion.ConsecutivoFacturaDao;
import co.innovate.rentavoz.services.facturacion.ConsecutivoFacturaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsecutivoFacturaServiceImpl
 * @date 21/04/2014
 *
 */
@Service("consecutivoFacturaService")
public class ConsecutivoFacturaServiceImpl extends GenericServiceImpl<ConsecutivoFactura, Integer> implements ConsecutivoFacturaService , Serializable	 {

	/**
	 * 21/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ConsecutivoFacturaDao consecutivoFacturaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<ConsecutivoFactura, Integer> getDao() {
		return consecutivoFacturaDao;
	}
}
