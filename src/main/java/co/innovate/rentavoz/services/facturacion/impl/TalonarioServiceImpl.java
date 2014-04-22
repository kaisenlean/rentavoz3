/**
 * 
 */
package co.innovate.rentavoz.services.facturacion.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.facturacion.TalonarioDao;
import co.innovate.rentavoz.services.facturacion.TalonarioService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TalonarioServiceImpl
 * @date 21/04/2014
 *
 */
@Service("talonarioService")
public class TalonarioServiceImpl extends GenericServiceImpl<Talonario, String> implements TalonarioService,Serializable{

	/**
	 * 21/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TalonarioDao talonarioDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Talonario, String> getDao() {
		return talonarioDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.TalonarioService#cargarConsecutivoFactura(co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public Talonario cargarConsecutivoFactura(Sucursal sucursal)
			throws BaseException {
		return talonarioDao.cargarConsecutivoFactura(sucursal);
	}
}
