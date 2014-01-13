/**
 * 
 */
package co.innovate.rentavoz.services.estadolinea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.estadolinea.EstadoLineaDao;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EstadoLineaServiceImpl
 * @date 13/01/2014
 *
 */
@Service("estadoLineaService")
public class EstadoLineaServiceImpl extends GenericServiceImpl<EstadoLinea, Integer> implements Serializable,EstadoLineaService {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EstadoLineaDao estadoLineaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<EstadoLinea, Integer> getDao() {
		return estadoLineaDao;
	}
	
}
