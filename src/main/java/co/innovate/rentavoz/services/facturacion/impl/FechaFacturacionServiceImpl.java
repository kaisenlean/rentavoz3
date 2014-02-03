/**
 * 
 */
package co.innovate.rentavoz.services.facturacion.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacionServiceImpl
 * @date 26/01/2014
 *
 */
 @Service("fechaFacturacionService")
public class FechaFacturacionServiceImpl extends GenericServiceImpl<FechaFacturacion, Integer> implements Serializable,FechaFacturacionService {

	 /**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	 private FechaFacturacionDao fechaFacturacionDao;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<FechaFacturacion, Integer> getDao() {
		return fechaFacturacionDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.FechaFacturacionService#findActivas()
	 */
	@Override
	public List<FechaFacturacion> findActivas() {
		return fechaFacturacionDao.findActivas();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.facturacion.FechaFacturacionService#findByFecha(java.util.Date)
	 */
	@Override
	public FechaFacturacion findByFecha(Date fecha) {
		return fechaFacturacionDao.findByFecha(fecha);
	}
}
