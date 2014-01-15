/**
 * 
 */
package co.innovate.rentavoz.services.venta.item.cuota.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.venta.item.cuota.VentaItemCuotaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.venta.item.cuota.VentaItemCuotaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemCuotaServiceImpl
 * @date 15/01/2014
 *
 */
@Service("ventaItemCuotaService")
public class VentaItemCuotaServiceImpl extends GenericServiceImpl<VentaItemCuota, Integer> implements VentaItemCuotaService,Serializable {

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaItemCuotaDao ventaItemCuotaDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<VentaItemCuota, Integer> getDao() {
		return ventaItemCuotaDao;
	}

}
