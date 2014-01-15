/**
 * 
 */
package co.innovate.rentavoz.services.bodegaingreso.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegaingreso.BodegaIngresoDao;
import co.innovate.rentavoz.services.bodegaingreso.BodegaIngresoService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaIngresoService
 * @date 13/01/2014
 *
 */
@Service("bodegaIngresoService")
public class BodegaIngresoServiceImpl  extends GenericServiceImpl<BodegaIngreso, Integer> implements Serializable,BodegaIngresoService{

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BodegaIngresoDao bodegaIngresoDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaIngreso, Integer> getDao() {
		return bodegaIngresoDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaingreso.BodegaIngresoService#findByFechas(java.util.Date, java.util.Date)
	 */
	@Override
	public List<BodegaExistencia> findByFechas(Date start, Date end) {
		
		return bodegaIngresoDao.findByFechas(start, end);
	}
}
