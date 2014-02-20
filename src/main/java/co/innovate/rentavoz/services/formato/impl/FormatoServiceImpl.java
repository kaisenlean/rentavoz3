/**
 * 
 */
package co.innovate.rentavoz.services.formato.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.formato.Formato;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.formato.FormatoDao;
import co.innovate.rentavoz.services.formato.FormatoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FormatoServiceImpl
 * @date 19/02/2014
 *
 */
@Service("formatoService")
public class FormatoServiceImpl extends GenericServiceImpl<Formato, Integer> implements FormatoService , Serializable {

	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FormatoDao formatoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Formato, Integer> getDao() {
		return formatoDao;
	}
}
