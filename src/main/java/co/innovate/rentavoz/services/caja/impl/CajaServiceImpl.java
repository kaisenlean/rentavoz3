/**
 * 
 */
package co.innovate.rentavoz.services.caja.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.caja.Caja;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.caja.CajaDao;
import co.innovate.rentavoz.services.caja.CajaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class CajaServiceImpl
 * @date 12/01/2014
 * 
 */
@Service(CajaServiceImpl.SERVICE_NAME)
public class CajaServiceImpl extends GenericServiceImpl<Caja, Integer>
		implements CajaService {

	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SERVICE_NAME
	 */
	static final String SERVICE_NAME = "cajaService";
	
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * cajaDao
	 */
	@Autowired
	private CajaDao cajaDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.services.caja.CajaService#abrirCaja(co.innovate.
	 * rentavoz.model.profile.Usuario)
	 */
	@Override
	public void abrirCaja(Usuario usuario)throws BaseException {
		cajaDao.abrirCaja(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCaja()
	 */
	@Override
	public double valorCaja() throws BaseException {
		return cajaDao.valorCaja();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Caja, Integer> getDao() {
		return cajaDao;
	}

}
