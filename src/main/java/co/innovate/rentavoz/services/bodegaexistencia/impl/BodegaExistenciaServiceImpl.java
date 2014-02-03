/**
 * 
 */
package co.innovate.rentavoz.services.bodegaexistencia.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.bodegaexistencia.BodegaExistenciaDao;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaServiceImpl
 * @date 13/01/2014
 *
 */
@Service("bodegaExistenciaService")
public class BodegaExistenciaServiceImpl extends GenericServiceImpl<BodegaExistencia, Integer> implements Serializable , BodegaExistenciaService {

	/**
	 * 14/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BodegaExistenciaDao bodegaExistenciaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByBarcode(java.lang.String)
	 */
	@Override
	public BodegaExistencia findByBarcode(String productoId) {
		return bodegaExistenciaDao.findByBarcode(productoId);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByBarcode(java.lang.String, co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public BodegaExistencia findByBarcode(String productoId, List<Sucursal> sucursal) {
		return bodegaExistenciaDao.findByBarcode(productoId, sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByBarcodeActivo(java.lang.String)
	 */
	@Override
	public BodegaExistencia findByBarcodeActivo(String productoId) {
		return bodegaExistenciaDao.findByBarcodeActivo(productoId);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByBarcodeActivo(java.lang.String, co.innovate.rentavoz.model.Sucursal)
	 */
	@Override
	public BodegaExistencia findByBarcodeActivo(String productoId,
			Sucursal sucursal) {
		return bodegaExistenciaDao.findByBarcodeActivo(productoId, sucursal);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByItemAndSucursal(co.innovate.rentavoz.model.Sucursal, co.innovate.rentavoz.model.bodega.BodegaItem)
	 */
	@Override
	public List<BodegaExistencia> findByItemAndSucursal(List<Sucursal> sucursal,
			BodegaItem bodegaItem) {
		return bodegaExistenciaDao.findByItemAndSucursal(sucursal, bodegaItem);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<BodegaExistencia, Integer> getDao() {
		return bodegaExistenciaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#findByIngreso(co.innovate.rentavoz.model.bodega.BodegaIngreso)
	 */
	@Override
	public List<BodegaExistencia> findByIngreso(BodegaIngreso bodegaIngreso) {
		return bodegaExistenciaDao.findByIngreso(bodegaIngreso);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService#deleteFromBodegaIngreso(co.innovate.rentavoz.model.bodega.BodegaIngreso)
	 */
	@Override
	public void deleteFromBodegaIngreso(BodegaIngreso bodegaIngreso) {
		bodegaExistenciaDao.deleteFromBodegaIngreso(bodegaIngreso);
	}
}
