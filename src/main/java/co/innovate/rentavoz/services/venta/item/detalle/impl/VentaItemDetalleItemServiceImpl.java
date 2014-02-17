/**
 * 
 */
package co.innovate.rentavoz.services.venta.item.detalle.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.venta.item.detalle.VentaItemDetalleItemDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.venta.item.detalle.VentaItemDetalleItemService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDetalleItemServiceImpl
 * @date 17/02/2014
 *
 */
@Service("VentaItemDetalleItemService")
public class VentaItemDetalleItemServiceImpl extends GenericServiceImpl<VentaItemDetalleItem, Integer> implements VentaItemDetalleItemService,Serializable{

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaItemDetalleItemDao ventaItemDetalleItemDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<VentaItemDetalleItem, Integer> getDao() {
		return ventaItemDetalleItemDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.item.detalle.VentaItemDetalleItemService#findByVentaItem(co.innovate.rentavoz.model.venta.VentaItem)
	 */
	@Override
	public List<VentaItemDetalleItem> findByVentaItem(VentaItem ventaItem) {
		return ventaItemDetalleItemDao.findByVentaItem(ventaItem);
	}
}
