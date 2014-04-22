/**
 * 
 */
package co.innovate.rentavoz.logic.venta.linea.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.venta.linea.VentaControllerService;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaService;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.facturacion.TalonarioService;
import co.innovate.rentavoz.services.linea.LineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaServiceImpl
 * @date 4/02/2014
 * 
 */
@Service("ventaControllerService")
public class VentaControllerServiceImpl implements VentaControllerService {


	@Autowired
	private VentaService ventaService;

	@Autowired
	private VentaLineaService ventaLineaService;
	
	@Autowired
	private TalonarioService talonarioService;
	
	@Autowired
	private CuotaService cuotaService;

	private Logger logger = Logger.getLogger(VentaControllerServiceImpl.class);

	@Autowired
	private EstadoLineaService estadoLineaService;
	
	
	@Autowired
	private LineaService lineaService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.logic.venta.linea.VentaControllerService#
	 * guardarVentaLinea(co.innovate.rentavoz.model.almacen.Venta)
	 */
	@Override
	public Venta guardarVentaLinea(Venta venta) throws BaseException {
		if (venta.getIdVenta() == null) {
			logger.info("====== Guardando nueva venta =========");
		}
		List<VentaLinea> lineas = new ArrayList<VentaLinea>();
		List<Cuota> cuotas= new ArrayList<Cuota>();
		cuotas=venta.getCuotas();
		lineas = venta.getVentaLineaList();
		Talonario talonario= talonarioService.cargarConsecutivoFactura(venta.getSucursal());
		talonario.setUsado(Boolean.TRUE);
		talonario=talonarioService.save(talonario);
		venta.setNumeroFactura(talonario);
		venta = ventaService.save(venta);
		int i = 0;
		for (VentaLinea linea : lineas) {
			linea.setVentaidVenta(venta);
			linea.setFechaRenovacion(calcularRenovacion(
					linea.getLineaidLinea(), venta));
			
			linea = ventaLineaService.save(linea);
			/*cambiando el estado de la linea a vendida*/
			Linea lineaVenta=linea.getLineaidLinea();
			lineaVenta.setEstadoLineaidEstadoLinea(estadoLineaService.findById(EstadoLineaService.ESTADO_VENDIDA));
			lineaService.save(lineaVenta);
			lineas.set(i, linea);
			i++;
		}
		venta.setVentaLineaList(lineas);
		i=0;
		for (Cuota cuota : cuotas) {
			cuota.setVenta(venta);
			cuota=cuotaService.save(cuota);
			cuotas.set(i, cuota);
		}
		venta.setCuotas(cuotas);
		logger.info("=== Venta almacenada con exito ====");
		
		return venta;

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/02/2014
	 * @param linea
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Date calcularRenovacion(Linea linea, Venta venta) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, linea.getLinCorte());
		calendar.set(Calendar.MONTH, venta.getFechaFacturacion()
				.getFechaInicio().getMonth());

		calendar.add(Calendar.MONTH, calendar.get(Calendar.MONTH));
		Date fecha = calendar.getTime();

		return fecha;
	}

}
