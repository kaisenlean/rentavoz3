/**
 * 
 */
package co.innovate.rentavoz.services.bodega.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodega.TipoInventarioDao;
import co.innovate.rentavoz.services.bodega.TipoInventarioService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventarioService
 * @date 7/04/2014
 *
 */
@Service("tipoInventarioService")
public class TipoInventarioServiceImpl extends GenericServiceImpl<TipoInventario,Integer> implements TipoInventarioService {

	@Autowired
	private TipoInventarioDao tipoInventarioDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<TipoInventario, Integer> getDao() {
		return tipoInventarioDao;
	}
}
