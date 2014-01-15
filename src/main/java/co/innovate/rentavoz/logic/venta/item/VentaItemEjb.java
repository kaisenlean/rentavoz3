package co.innovate.rentavoz.logic.venta.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.EstadoExistenciaEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.venta.item.VentaItemService;
import co.innovate.rentavoz.services.venta.item.cuota.VentaItemCuotaService;

/**
 * Clase EJB que controla toda la transaccion de una venta de un articulo de bodega
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class VentaItemEjb
 * @date 29/10/2013
 *
 */
@ManagedBean
@SessionScoped
public class VentaItemEjb implements Serializable {

	/**
	 * 29/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{ventaItemService}")
	private VentaItemService ventaItemService;
	
	@ManagedProperty(value="#{bodegaExistenciaService}")
	private BodegaExistenciaService bodegaExistenciaService;
	
	@ManagedProperty(value="#{ventaItemCuotaService}")
	private VentaItemCuotaService ventaItemCuotaService;
	
	
	/**
	 * Metodo que registra la venta de las existencias seleccionadas , cambia el estado de las mismas 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param venta
	* @return {@link VentaItem}
	 */
	public VentaItem registrarVenta(VentaItem venta) {
		List<VentaItemDetalleItem> detallesVenta= venta.getExistencias();
		List<BodegaExistencia>  existencias = new ArrayList<BodegaExistencia>();
		/*sacamos las existencias involucradas en la venta*/
		for (VentaItemDetalleItem detalleItem : detallesVenta) {
			BodegaExistencia beTemp=detalleItem.getExistencia();
			beTemp.setEstado(EstadoExistenciaEnum.VENDIDO);
			existencias.add(beTemp);
		}
		/*registramos la venta */		
		venta=ventaItemService.save(venta);
		
		/*luego editamos las existencias a vendida*/
		for (BodegaExistencia bodegaExistencia : existencias) {
			bodegaExistenciaService.save(bodegaExistencia);
		}
		
		for (VentaItemCuota cuota : venta.getCuotas()) {
			cuota.setIdVenta(venta);
			ventaItemCuotaService.save(cuota);
			
		}
		
		return venta;
		
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param ventaItemService the ventaItemService to set
	 */
	public void setVentaItemService(VentaItemService ventaItemService) {
		this.ventaItemService = ventaItemService;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param bodegaExistenciaService the bodegaExistenciaService to set
	 */
	public void setBodegaExistenciaService(
			BodegaExistenciaService bodegaExistenciaService) {
		this.bodegaExistenciaService = bodegaExistenciaService;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param ventaItemCuotaService the ventaItemCuotaService to set
	 */
	public void setVentaItemCuotaService(VentaItemCuotaService ventaItemCuotaService) {
		this.ventaItemCuotaService = ventaItemCuotaService;
	}
	
	
	

}
