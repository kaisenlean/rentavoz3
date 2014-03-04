/**
 * 
 */
package co.innovate.rentavoz.services.almacen.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.almacen.CuotaDao;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CuotaServiceImpl
 * @date 7/02/2014
 *
 */
@Service("cuotaService")
public class CuotaServiceImpl extends GenericServiceImpl<Cuota, Integer> implements CuotaService,Serializable {

	/**
	 * 7/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private CuotaDao cuotaDao;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<Cuota, Integer> getDao() {
		return cuotaDao;
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.CuotaService#buscarCuotasPendientesPorCliente(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<Cuota> buscarCuotasPendientesPorCliente(Tercero cliente) {
		return cuotaDao.buscarCuotasPendientesPorCliente(cliente);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.CuotaService#buscarRutaDeCuotasPorCobrador(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<Cuota> buscarRutaDeCuotasPorCobrador(Tercero cobrador) {
		return cuotaDao.buscarRutaDeCuotasPorCobrador(cobrador);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.CuotaService#findByVenta(co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@Override
	public List<Cuota> findByVenta(Venta venta) {
		return cuotaDao.findByVenta(venta);
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.CuotaService#findDeudoresMorosos(java.util.Date)
	 */
	@Override
	public List<Tercero> findDeudoresMorosos(Date fechaCierre) {
		return cuotaDao.findDeudoresMorosos(fechaCierre);
	}

}
