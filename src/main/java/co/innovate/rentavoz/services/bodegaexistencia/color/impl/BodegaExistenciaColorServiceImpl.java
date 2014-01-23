/**
 * 
 */
package co.innovate.rentavoz.services.bodegaexistencia.color.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.BodegaExistenciaColor;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegaexistencia.color.BodegaExistenciaColorDao;
import co.innovate.rentavoz.services.bodegaexistencia.color.BodegaExistenciaColorService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaColorServiceImpl
 * @date 19/01/2014
 *
 */
@Service("bodegaExistenciaColorService")
public class BodegaExistenciaColorServiceImpl extends GenericServiceImpl<BodegaExistenciaColor, Integer> implements BodegaExistenciaColorService,Serializable {

	/**
	 * 19/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BodegaExistenciaColorDao bodegaExistenciaColorDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaExistenciaColor, Integer> getDao() {
		return bodegaExistenciaColorDao;
	}
}
