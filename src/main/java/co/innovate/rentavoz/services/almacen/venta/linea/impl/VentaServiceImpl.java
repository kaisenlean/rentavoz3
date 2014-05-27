/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.almacen.venta.VentaDao;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaServiceImpl
 * @date 5/02/2014
 *
 */
@Service("ventaService")
public class VentaServiceImpl extends GenericServiceImpl<Venta, Integer> implements Serializable , VentaService{

	
	/**
	 * 5/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaDao ventaDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Venta, Integer> getDao() {
		return ventaDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaService#findByConsecutivo(java.lang.String)
	 */
	@Override
	public Venta findByConsecutivo(String consecutivo) {
		return ventaDao.findByConsecutivo(consecutivo);
	}

}
