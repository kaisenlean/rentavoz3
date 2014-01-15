/**
 * 
 */
package co.innovate.rentavoz.services.cuenta.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.cuenta.CuentasDao;
import co.innovate.rentavoz.services.cuenta.CuentasService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuentasServiceImpl
 * @date 15/01/2014
 *
 */
@Service("cuentasService")
public class CuentasServiceImpl extends GenericServiceImpl<Cuentas, Integer> implements CuentasService,Serializable {
/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
@Autowired
private CuentasDao cuentasDao;

/* (non-Javadoc)
 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
 */
@Override
public GenericRepository<Cuentas, Integer> getDao() {
	return cuentasDao;
}



}
