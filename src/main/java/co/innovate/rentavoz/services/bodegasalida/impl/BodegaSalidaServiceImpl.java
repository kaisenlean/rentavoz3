/**
 * 
 */
package co.innovate.rentavoz.services.bodegasalida.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegasalida.BodegaSalidaDao;
import co.innovate.rentavoz.services.bodegasalida.BodegaSalidaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaSalidaServiceImpl
 * @date 14/01/2014
 *
 */
@Service("bodegaSalidaService")
public class BodegaSalidaServiceImpl extends GenericServiceImpl<BodegaSalida, Integer> implements Serializable,BodegaSalidaService{

	
	/**
	 * 14/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BodegaSalidaDao bodegaSalidaDao;
	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaSalida, Integer> getDao() {
		return bodegaSalidaDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegasalida.BodegaSalidaService#findByFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public List<BodegaSalidaReferencia> findByFechas(Date start, Date end) {
		return bodegaSalidaDao.findByFechas(start, end);
	}

}
