/**
 * 
 */
package co.innovate.rentavoz.services.caja.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.caja.Caja;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
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
		implements CajaService, Serializable {

	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

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
	public double valorCaja(Tercero vendedor) throws BaseException {
		return cajaDao.valorCaja(vendedor);
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

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCajaLineas()
	 */
	@Override
	public double valorCajaLineas(Tercero vendedor) throws BaseException {
		return cajaDao.valorCajaLineas(vendedor);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCajaLineasBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public double valorCajaLineasBySucursal(Sucursal sucursal, Date fecha) {
		return cajaDao.valorCajaLineasBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCajaLineasBySucursalDetalle(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<Cuota> valorCajaLineasBySucursalDetalle(Sucursal sucursal,
			Date fecha) {
		return cajaDao.valorCajaLineasBySucursalDetalle(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCajaEquiposBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public double valorCajaEquiposBySucursal(Sucursal sucursal, Date fecha) {
		return cajaDao.valorCajaEquiposBySucursal(sucursal, fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.caja.CajaService#valorCajaEquiposBySucursalDetalle(co.innovate.rentavoz.model.Sucursal, java.util.Date)
	 */
	@Override
	public List<VentaItemCuota> valorCajaEquiposBySucursalDetalle(
			Sucursal sucursal, Date fecha) {
		return cajaDao.valorCajaEquiposBySucursalDetalle(sucursal, fecha);
	}

}
