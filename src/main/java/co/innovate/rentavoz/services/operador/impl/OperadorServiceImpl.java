/**
 * 
 */
package co.innovate.rentavoz.services.operador.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.operador.OperadorDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.operador.OperadorService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OperadorServiceImpl
 * @date 13/01/2014
 *
 */
@Service("operadorService")
public class OperadorServiceImpl extends GenericServiceImpl<Operador, Integer> implements OperadorService,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private OperadorDao operadorDao;
	
	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.operador.OperadorService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Operador> findByCriterio(String criterio) {
		return operadorDao.findByCriterio(criterio);
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Operador, Integer> getDao() {
		return operadorDao;
	}

}
