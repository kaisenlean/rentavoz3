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
import co.innovate.rentavoz.model.facturacion.NotaDebito;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.facturacion.NotaDebitoDao;
import co.innovate.rentavoz.services.facturacion.NotaDebitoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoServiceImpl
 * @date 11/03/2014
 *
 */
@Service("notaDebitoService")
public class NotaDebitoServiceImpl extends GenericServiceImpl<NotaDebito, Integer> implements NotaDebitoService, Serializable {

	/**
	 * 11/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private NotaDebitoDao notaDebitoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<NotaDebito, Integer> getDao() {
		return notaDebitoDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByCriteria(java.lang.String, int, int, org.hibernate.criterion.Order)
	 */
	@Override
	public List<NotaDebito> findByCriteria(String query, int firstResult,
			int maxResults, Order order) {
		return notaDebitoDao.findByCriteria(query, firstResult, maxResults, order);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#countByCriteria(java.lang.String)
	 */
	@Override
	public int countByCriteria(String query) {
		return notaDebitoDao.countByCriteria(query);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByFecha(java.util.Date)
	 */
	@Override
	public List<NotaDebito> findByFecha(Date fecha) {
		return notaDebitoDao.findByFecha(fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<NotaDebito> findByGenerador(Tercero tercero) {
		return notaDebitoDao.findByGenerador(tercero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumByGenerador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public double sumByGenerador(Tercero tercero) {
		return notaDebitoDao.sumByGenerador(tercero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public List<NotaDebito> findBySucursal(Sucursal sucursal) {
		return notaDebitoDao.findBySucursal(sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumBySucursal(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal) {
		return notaDebitoDao.sumBySucursal(sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public double sumBySucursal(Sucursal sucursal, Date fecha) {
		return notaDebitoDao.sumBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<NotaDebito> findBySucursal(Sucursal sucursal, Date fecha) {
		return notaDebitoDao.findBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#findByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public List<NotaDebito> findByGenerador(Tercero tercero, Date fecha) {
		return notaDebitoDao.findByGenerador(tercero, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.NotaCreditoService#sumByGenerador(co.innovate.rentavoz.model.Tercero, java.util.Date)
	 */
	@Override
	public double sumByGenerador(Tercero tercero, Date fecha) {
		return notaDebitoDao.sumByGenerador(tercero, fecha);
	}
	
	
	

}
