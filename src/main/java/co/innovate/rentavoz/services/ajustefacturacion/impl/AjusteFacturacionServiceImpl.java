/**
 * 
 */
package co.innovate.rentavoz.services.ajustefacturacion.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.ajustefactura.AjusteFacturaDto;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao;
import co.innovate.rentavoz.repositories.venta.item.VentaItemDao;
import co.innovate.rentavoz.services.ajustefacturacion.AjusteFacturacionService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class AjusteFacturacionServiceImpl
 * @date 23/04/2014
 * 
 */
@Service("ajusteFacturacionService")
public class AjusteFacturacionServiceImpl implements AjusteFacturacionService {

	@Autowired
	private VentaLineaDao ventaLineaDao;

	@Autowired
	private VentaItemDao ventaItemDao;

	@Autowired
	private FacturaControllerService facturaControllerService;

//	private Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.services.ajustefacturacion.AjusteFacturacionService
	 * #cargarAjuste(co.innovate.rentavoz.model.facturacion.FechaFacturacion)
	 */
	@Override
	public List<AjusteFacturaDto> cargarAjuste(
			FechaFacturacion fechaFacturacion, double meta) {
		List<AjusteFacturaDto> lista = new ArrayList<AjusteFacturaDto>();
		List<Venta> ajustadas = ventaLineaDao.findVentaAjuste(fechaFacturacion,
				Boolean.TRUE);

		double sumAjustadas = ventaLineaDao.findValorVentaAjuste(
				fechaFacturacion, Boolean.TRUE);

		List<Venta> noAjustadas = ventaLineaDao.findVentaAjuste(
				fechaFacturacion, Boolean.FALSE);

		double sumNoAjustadas = ventaLineaDao.findValorVentaAjuste(
				fechaFacturacion, Boolean.FALSE);


		
		
		lista.addAll(convertToDto(ajustadas));
		lista.addAll(convertToDto(noAjustadas));
		
		
		while (meta>(sumAjustadas+sumNoAjustadas)) {
			
		}

		return null;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/04/2014
	 * @param ajustadas
	 */
	private List<AjusteFacturaDto> convertToDto(List<Venta> lista) {

		List<AjusteFacturaDto> facturaDtos = new ArrayList<AjusteFacturaDto>();

		for (Venta venta : lista) {

			AjusteFacturaDto dto = new AjusteFacturaDto();
			dto.setFacturaLinea(Boolean.TRUE);
			dto.setVenta(venta);
			facturaDtos.add(dto);
		}

		return facturaDtos;
	}

}
