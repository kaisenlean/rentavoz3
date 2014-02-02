/**
 * 
 */
package co.innovate.rentavoz.services.ciudad.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.ciudad.CiudadDao;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CiudadServiceImpl
 * @date 13/01/2014
 *
 */
@Service("ciudadService")
public class CiudadServiceImpl extends GenericServiceImpl<Ciudad, Integer> implements Serializable,CiudadService {

	@Autowired
	private CiudadDao ciudadDao;
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Ciudad, Integer> getDao() {
		return ciudadDao;
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.ciudad.CiudadService#findByCriterio(java.lang.String, co.innovate.rentavoz.model.Departamento)
	 */
	@Override
	public List<Ciudad> findByCriterio(String criterio,
			Departamento departamento) {
		return ciudadDao.findByCriterio(criterio, departamento);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.ciudad.CiudadService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Ciudad> findByCriterio(String criterio) {
		return ciudadDao.findByCriterio(criterio);
	}

}
