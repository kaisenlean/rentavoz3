/**
 * 
 */
package co.innovate.rentavoz.services.centrope.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.centrope.CentropeDao;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CentropeServiceImpl
 * @date 26/01/2014
 *
 */
@Service("centropeService")
public class CentropeServiceImpl extends GenericServiceImpl<Centrope, Integer> implements CentropeService,Serializable{

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CentropeDao	 centropeDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Centrope, Integer> getDao() {
		return centropeDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.centrope.CentropeService#findByParametro(java.lang.String)
	 */
	@Override
	public List<Centrope> findByParametro(String parametro) {
		return centropeDao.findByParametro(parametro);
	}
}
