/**
 * 
 */
package co.innovate.rentavoz.services.empresa.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.empresa.EmpresaDao;
import co.innovate.rentavoz.services.empresa.EmpresaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EmpresaServiceImpl
 * @date 13/01/2014
 *
 */
@Service("empresaService")
public class EmpresaServiceImpl extends GenericServiceImpl<Empresa, Integer> implements Serializable,EmpresaService {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EmpresaDao empresaDao;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Empresa, Integer> getDao() {
		return empresaDao;
	} 
}
