/**
 * 
 */
package co.innovate.rentavoz.services.traslado.linea.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLineaDetalle;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.traslado.linea.TrasladoLineaDetalleDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.traslado.linea.TrasladoLineaDetalleService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaDetalleServiceImpl
 * @date 17/02/2014
 *
 */
@Service("trasladoLineaDetalleService")
public class TrasladoLineaDetalleServiceImpl extends GenericServiceImpl<TrasladoLineaDetalle, Integer> implements Serializable, TrasladoLineaDetalleService {

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TrasladoLineaDetalleDao trasladoLineaDetalleDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<TrasladoLineaDetalle, Integer> getDao() {
		return trasladoLineaDetalleDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.traslado.linea.TrasladoLineaDetalleService#findByTraslado(co.innovate.rentavoz.model.traslado.linea.TrasladoLinea)
	 */
	@Override
	public List<TrasladoLineaDetalle> findByTraslado(TrasladoLinea trasladoLinea) {
		return trasladoLineaDetalleDao.findByTraslado(trasladoLinea);
	}
}
