/**
 * 
 */
package co.innovate.rentavoz.services.traslado.linea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.traslado.linea.TrasladoLineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.traslado.linea.TrasladoLineaService;


/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLineaServiceImpl
 * @date 17/02/2014
 *
 */
@Service("trasladoLineaService")
public class TrasladoLineaServiceImpl extends GenericServiceImpl<TrasladoLinea, Integer> implements TrasladoLineaService,Serializable{

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TrasladoLineaDao trasladoLineaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<TrasladoLinea, Integer> getDao() {
		return trasladoLineaDao;
	}
	
	
	
}
