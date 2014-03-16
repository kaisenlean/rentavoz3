/**
 * 
 */
package co.innovate.rentavoz.services.facturacion.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
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

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByFecha(java.util.Date)
	 */
	@Override
	public List<NotaCredito> findByFecha(Date fecha) {
		return notaCreditoDao.findByFecha(fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<NotaCredito> findByGenerador(Tercero tercero) {
		return notaCreditoDao.findByGenerador(tercero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public double sumByGenerador(Tercero tercero) {
		return notaCreditoDao.sumByGenerador(tercero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public List<NotaCredito> findBySucursal(Sucursal sucursal) {
		return notaCreditoDao.findBySucursal(sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal) {
		return notaCreditoDao.sumBySucursal(sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal, Date fecha) {
		return notaCreditoDao.sumBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<NotaCredito> findBySucursal(Sucursal sucursal, Date fecha) {
		return notaCreditoDao.findBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public List<NotaCredito> findByGenerador(Tercero tercero, Date fecha) {
		return notaCreditoDao.findByGenerador(tercero, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public double sumByGenerador(Tercero tercero, Date fecha) {
		return notaCreditoDao.sumByGenerador(tercero, fecha);
	}
	
	
	

}
