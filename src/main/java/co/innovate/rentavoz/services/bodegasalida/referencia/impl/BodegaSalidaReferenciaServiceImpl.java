/**
 * 
 */
package co.innovate.rentavoz.services.bodegasalida.referencia.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegasalida.referencia.BodegaSalidaReferenciaDao;
import co.innovate.rentavoz.services.bodegasalida.referencia.BodegaSalidaReferenciaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaReferenciaServiceImpl
 * @date 14/01/2014
 *
 */
@Service("bodegaSalidaReferenciaService")
public class BodegaSalidaReferenciaServiceImpl extends GenericServiceImpl<BodegaSalidaReferencia, Integer> implements Serializable,BodegaSalidaReferenciaService{

	/**
	 * 14/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BodegaSalidaReferenciaDao bodegaSalidaReferenciaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaSalidaReferencia, Integer> getDao() {
		return bodegaSalidaReferenciaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegasalida.referencia.BodegaSalidaReferenciaService#findByBodegaSalida(co.innovate.rentavoz.model.bodega.BodegaSalida)
	 */
	@Override
	public List<BodegaSalidaReferencia> findByBodegaSalida(
			BodegaSalida bodegaSalida) {
		return bodegaSalidaReferenciaDao.findByBodegaSalida(bodegaSalida);
	}
	
	
}
