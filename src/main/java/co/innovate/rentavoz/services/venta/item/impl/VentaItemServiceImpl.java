/**
 * 
 */
package co.innovate.rentavoz.services.venta.item.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.venta.item.VentaItemDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.venta.item.VentaItemService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemServiceImpl
 * @date 15/01/2014
 *
 */
@Service("ventaItemService")
public class VentaItemServiceImpl extends GenericServiceImpl<VentaItem, Integer> implements VentaItemService,Serializable{

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaItemDao ventaItemDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<VentaItem, Integer> getDao() {
		return ventaItemDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.item.VentaItemService#findVentaByFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public List<VentaItem> findVentaByFechas(Date start, Date end,int firstResult,int maxResults) {
		return ventaItemDao.findVentaByFechas(start, end, firstResult, maxResults);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.item.VentaItemService#countFindVentaByFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public int countFindVentaByFechas(Date start, Date end) {
		return ventaItemDao.countFindVentaByFechas(start, end);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.item.VentaItemService#findByNumeroFactura(java.lang.String)
	 */
	@Override
	public VentaItem findByNumeroFactura(String consecutivo) {
		return ventaItemDao.findByNumeroFactura(consecutivo);
	}

}
