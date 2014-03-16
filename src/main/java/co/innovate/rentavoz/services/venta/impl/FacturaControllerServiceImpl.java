/**
 * 
 */
package co.innovate.rentavoz.services.venta.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.services.venta.item.VentaItemService;
import co.innovate.rentavoz.services.venta.item.cuota.VentaItemCuotaService;
import co.innovate.rentavoz.services.venta.item.detalle.VentaItemDetalleItemService;

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
	
	@Autowired
	private VentaItemService ventaItemService;
	
	
	@Autowired
	private VentaItemCuotaService ventaItemCuotaService;
	
	@Autowired
	private VentaItemDetalleItemService ventaItemDetalleItemService;

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
		
		if (cuota.getValorCuota2()!=null) {
			if (cuota.getValorCuota2().doubleValue()>0) {
				
				double pendiente = cuota.getValorCuota().doubleValue()-cuota.getValorCuota2().doubleValue();
				
				cuota.setValorCuota(cuota.getValorCuota2());
				
				
				Cuota cuota2 = new Cuota();
				cuota2.setEstadoCuota(EstadoCuotaEnum.PENDIENTE);
				cuota2.setValorCuota(BigDecimal.valueOf(pendiente));
				cuota2.setFechaPago(cuota.getFechaSgtePago()==null?Calendar.getInstance().getTime():cuota.getFechaSgtePago());
				cuota2.setVenta(cuota.getVenta());
				cuotaService.save(cuota2);
			}
		}
		cuotaService.save(cuota);
		return cargarFactura(cuota.getVenta());
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.FacturaControllerService#cargarFacturaItem(co.innovate.rentavoz.model.venta.VentaItem)
	 */
	@Override
	public VentaItem cargarFacturaItem(VentaItem venta) {
		
		venta.setCuotas(ventaItemCuotaService.findCuotasByVenta(venta));
		venta.setExistencias(ventaItemDetalleItemService.findByVentaItem(venta));
		return venta;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.venta.FacturaControllerService#pagarCuotaItem(co.innovate.rentavoz.model.venta.VentaItemCuota)
	 */
	@Override
	public VentaItem pagarCuotaItem(VentaItemCuota cuota) {
		cuota.setEstado(EstadoVentaItemCuotaEnum.PAGADA);
		cuota.setFechaPago(Calendar.getInstance().getTime());
		ventaItemCuotaService.save(cuota);
		return cargarFacturaItem(cuota.getIdVenta());
	}
	
	

}
