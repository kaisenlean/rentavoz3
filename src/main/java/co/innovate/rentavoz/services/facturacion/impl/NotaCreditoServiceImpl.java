/**
 * 
 */
package co.innovate.rentavoz.services.facturacion.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.facturacion.NotaCredito;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao;
import co.innovate.rentavoz.services.facturacion.NotaCreditoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoServiceImpl
 * @date 11/03/2014
 *
 */
@Service("notaCreditoService")
public class NotaCreditoServiceImpl extends GenericServiceImpl<NotaCredito, Integer> implements NotaCreditoService, Serializable {

	/**
	 * 11/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private NotaCreditoDao notaCreditoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<NotaCredito, Integer> getDao() {
		return notaCreditoDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByCriteria(java.lang.String, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public List<NotaCredito> findByCriteria(String query, int firstResult,
			int maxResults, Order order) {
		return notaCreditoDao.findByCriteria(query, firstResult, maxResults, order);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#countByCriteria(java.lang.String)
	 */
	@Override
	public int countByCriteria(String query) {
		return notaCreditoDao.countByCriteria(query);
	}
	
	
	

}
