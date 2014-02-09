/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaServiceImpl
 * @date 5/02/2014
 *
 */
@Service("ventaLineaService")
public class VentaLineaServiceImpl extends GenericServiceImpl<VentaLinea, Integer> implements Serializable,VentaLineaService {

	/**
	 * 5/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaLineaDao ventaLineaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<VentaLinea, Integer> getDao() {
		return ventaLineaDao;
	}
}
