/**
 * 
 */
package co.innovate.rentavoz.views.venta.linea.devolucion;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.EstadoDevolucionEnum;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.repositories.linea.impl.LineaDaoImpl;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.reports.PrinterBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanDevolucionSim
 * @date 8/03/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanDevolucionSim extends BaseBean implements Serializable {

	/**
	 * 8/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{ventaLineaService}")
	private VentaLineaService ventaLineaService;
	
	
	
	private List<VentaLinea> lineas;
	
	private Tercero tercero;
	
	
	private AutocompleteTercero autocompleteTercero;
	
	
	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	
	@ManagedProperty(value="#{lineaService}")
	private LineaService lineaService;
	
	@ManagedProperty(value="#{estadoLineaService}")
	private EstadoLineaService estadoLineaService;

	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	

	
	@PostConstruct
	public void init(){
		
		lineas=new ArrayList<VentaLinea>();
		autocompleteTercero=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				tercero=seleccionado;
				buscar();
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
	}
	
	
	public void realizarDevolucion(VentaLinea linea){
		linea.setEstadoDevolucion(EstadoDevolucionEnum.REALIZADA);
		linea.setFechaDevolucion(Calendar.getInstance().getTime());
		Linea l= linea.getLineaidLinea();
		l.setEstadoLineaidEstadoLinea(estadoLineaService.findById(LineaDaoImpl.ESTADO_LINEA_REPO));
		lineaService.save(l);
		ventaLineaService.save(linea);
		DevolucionDto dto= new DevolucionDto();
		
		dto.setCliente(linea.getVentaidVenta().getTercero());
		dto.setVendedor(linea.getVentaidVenta().getVendedor());
		dto.setValorDevolucion(linea.getVentLinDeposito().doubleValue());
		List<DevolucionDto> lista= new ArrayList<DevolucionDto>();
		lista.add(dto);
		lineas.remove(linea);
		 addAttribute(SessionParams.ENTITY_BACK, lista);
		runJavascript("window.open('respuesta.jsf','_blank','width=900px,height=300px');");
		
		
		
	}
	
	
	public void realizarDevoluciones(){
		List<DevolucionDto> lista= new ArrayList<DevolucionDto>();
		Tercero cliente=null;
		Tercero vendedor=null;
		double total = BigInteger.ZERO.doubleValue();
		for (VentaLinea linea : lineas) {
			if (linea.isSeleccionado()) {
				linea.setEstadoDevolucion(EstadoDevolucionEnum.REALIZADA);
				linea.setFechaDevolucion(Calendar.getInstance().getTime());
				Linea l= linea.getLineaidLinea();
				l.setEstadoLineaidEstadoLinea(estadoLineaService.findById(LineaDaoImpl.ESTADO_LINEA_REPO));
				lineaService.save(l);
				ventaLineaService.save(linea);
				DevolucionDto dto= new DevolucionDto();
				if (cliente==null) {
					cliente=linea.getVentaidVenta().getTercero();
				}
				if (vendedor==null) {
					vendedor=linea.getVentaidVenta().getVendedor();
				}
				total+=linea.getVentLinDeposito().doubleValue();
				dto.setCliente(linea.getVentaidVenta().getTercero());
				dto.setVendedor(linea.getVentaidVenta().getVendedor());
				dto.setValorDevolucion(linea.getVentLinDeposito().doubleValue());
				
	
				lista.add(dto);
			}
		}
		List<DevolucionDto> lista2= new ArrayList<DevolucionDto>();
		if (lista.isEmpty()) {
			mensajeError("No se ha seleccionado ningun deposito");
			return;
		}
		DevolucionDto dto= new DevolucionDto();
		dto.setCliente(cliente);
		dto.setVendedor(vendedor);
		dto.setValorDevolucion(total);
		lista2.add(dto);
		 addAttribute(SessionParams.ENTITY_BACK, lista2);
		runJavascript("window.open('respuesta.jsf','_blank','width=900px,height=300px');");
		
		
	}
	
	public void buscar(){
		
		lineas=ventaLineaService.findLineasConDevolucionByCliente(tercero);
		
		if (lineas.isEmpty()) {
			mensajeError("No se han encontrado devoluciones para este cliente");
		}
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param ventaLineaService the ventaLineaService to set
	 */
	public void setVentaLineaService(VentaLineaService ventaLineaService) {
		this.ventaLineaService = ventaLineaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @return the lineas
	 */
	public List<VentaLinea> getLineas() {
		return lineas;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param lineas the lineas to set
	 */
	public void setLineas(List<VentaLinea> lineas) {
		this.lineas = lineas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param terceroService the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param autocompleteTercero the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param lineaService the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/03/2014
	 * @param estadoLineaService the estadoLineaService to set
	 */
	public void setEstadoLineaService(EstadoLineaService estadoLineaService) {
		this.estadoLineaService = estadoLineaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
}
