/**
 * 
 */
package co.innovate.rentavoz.services.venta.impl;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FacturaControllerServiceImpl
 * @date 10/02/2014
 *
 */
@Service("facturaControllerService")
public class FacturaControllerServiceImpl implements FacturaControllerService, Serializable
{

	/**
	 * 10/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private CuotaService cuotaService;
	
	@Autowired
	private VentaLineaService ventaLineaService;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.FacturaControllerService#cargarFactura(co.innovate.rentavoz.model.almacen.venta.Venta)
	 */
	@Override
	public Venta cargarFactura(Venta venta) {
		venta.setCuotas(cuotaService.findByVenta(venta));
		venta.setVentaLineaList(ventaLineaService.findByVenta(venta));
		return venta;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.FacturaControllerService#pagarCuota(java.util.List)
	 */
	@Override
	public Venta pagarCuota(Cuota cuota) {
		cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
		cuota.setFechaPago(Calendar.getInstance().getTime());
		cuota=cuotaService.save(cuota);
		return cargarFactura(cuota.getVenta());
	}
	
	

}
