/**
 * 
 */
package co.innovate.rentavoz.services.almacen.venta.linea.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaServiceImpl
 * @date 5/02/2014
 *
 */
@Service("ventaLineaService")
public class VentaLineaServiceImpl extends GenericServiceImpl<VentaLinea, Integer> implements Serializable,VentaLineaService {

	/**
	 * 5/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaLineaDao ventaLineaDao;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<VentaLinea, Integer> getDao() {
		return ventaLineaDao;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#findByVenta(co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@Override
	public List<VentaLinea> findByVenta(Venta venta) {
		return ventaLineaDao.findByVenta(venta);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#findHistorialFacturacion(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@Override
	public List<VentaLinea> findHistorialFacturacion(Linea linea) {
		return ventaLineaDao.findHistorialFacturacion(linea);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#findLineasConDevolucionByCliente(co.innovate.rentavoz.model.Tercero)
	 */
	@Override
	public List<VentaLinea> findLineasConDevolucionByCliente(Tercero tercero) {
		return ventaLineaDao.findLineasConDevolucionByCliente(tercero);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#findByCriterio(int, int, org.hibernate.criterion.Order, java.lang.String, java.lang.String, int, co.innovate.rentavoz.model.facturacion.FechaFacturacion)
	 */
	@Override
	public List<VentaLinea> findByCriterio(int firstResul, int maxResults,
			Order order, String numeroLinea, Tercero cliente, int corte,
			FechaFacturacion fechaFacturacion,Date fecha,Date fechaLim) {
		return ventaLineaDao.findByCriterio(firstResul, maxResults, order, numeroLinea, cliente, corte, fechaFacturacion,fecha,fechaLim);
				
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#countdByCriterio(java.lang.String, java.lang.String, int, co.innovate.rentavoz.model.facturacion.FechaFacturacion, java.util.Date)
	 */
	@Override
	public int countdByCriterio(String numeroLinea, Tercero cliente, int corte,
			FechaFacturacion fechaFacturacion, Date fecha,Date fechaLim) {
		return ventaLineaDao.countdByCriterio(numeroLinea, cliente, corte, fechaFacturacion, fecha,fechaLim);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#sumByCriterio(java.lang.String, java.lang.String, int, co.innovate.rentavoz.model.facturacion.FechaFacturacion, java.util.Date)
	 */
	@Override
	public double sumByCriterio(String numeroLinea, Tercero cliente, int corte,
			FechaFacturacion fechaFacturacion, Date fecha,Date fechaLim) {
		return ventaLineaDao.sumByCriterio(numeroLinea, cliente, corte, fechaFacturacion, fecha,fechaLim);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService#sumByCriterioCompra(java.lang.String, java.lang.String, int, co.innovate.rentavoz.model.facturacion.FechaFacturacion, java.util.Date)
	 */
	@Override
	public double sumByCriterioCompra(String numeroLinea, Tercero cliente,
			int corte, FechaFacturacion fechaFacturacion, Date fecha,Date fechaLim) {
		return ventaLineaDao.sumByCriterioCompra(numeroLinea, cliente, corte, fechaFacturacion, fecha,fechaLim);
	}
}
