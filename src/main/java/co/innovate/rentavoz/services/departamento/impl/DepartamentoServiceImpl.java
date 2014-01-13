/**
 * 
 */
package co.innovate.rentavoz.services.departamento.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.departamento.DepartamentoDao;
import co.innovate.rentavoz.services.departamento.DepartamentoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class DepartamentoServiceImpl
 * @date 13/01/2014
 *
 */
@Service("departamentoService")
public class DepartamentoServiceImpl extends GenericServiceImpl<Departamento, Integer> implements DepartamentoService , Serializable {

	@Autowired
	private DepartamentoDao departamentoDao;
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.departamento.DepartamentoService#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Departamento> findByCriterio(String criterio) {
		return departamentoDao.findByCriterio(criterio);
	}

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Departamento, Integer> getDao() {
		return departamentoDao;
	}

}
