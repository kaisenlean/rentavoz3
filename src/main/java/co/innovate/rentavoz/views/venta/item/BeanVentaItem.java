package co.innovate.rentavoz.views.venta.item;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.logic.venta.item.VentaItemEjb;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TipoTerceroEnum;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.EstadoVentaItemEnum;
import co.innovate.rentavoz.model.venta.ModoPagoEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.cuenta.CuentasService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanVentaItem
 * @date 29/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanVentaItem extends BaseBean implements Serializable {

	
	
	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * vt
	 */
	private VentaItem vt;
	/**
	 * 
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ventas
	 */
	List<VentaItem> ventas= new ArrayList<VentaItem>();
	
	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         login
	 */
	@ManagedProperty(value = "#{login}")
	private Login login;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ventaItemEjb
	 */
	@ManagedProperty(value="#{ventaItemEjb}")
	private VentaItemEjb ventaItemEjb;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         venta
	 */
	private VentaItem venta;

	@ManagedProperty(value="#{bodegaExistenciaService}")
	private BodegaExistenciaService bodegaExistenciaService;

	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean  printerBean;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * productoId
	 */
	private String productoId;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * tercero
	 */
	private Tercero tercero;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * autocompleteTercero
	 */
	private AutocompleteTercero autocompleteTercero;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * idCuenta
	 */
	private int idCuenta;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * modoPago
	 */
	private String modoPago;

	/**
	 * 30/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * cuota
	 */
	private VentaItemCuota cuota;

	/**
	 * 30/10/2013r
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * estadoCuota
	 */
	private String estadoCuota;
	
	@ManagedProperty(value="#{cuentasService}")
	private CuentasService  cuentasService;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	@PostConstruct
	public void init() {

		inicializarVenta();

		tercero = new Tercero();
		cuota = new VentaItemCuota();
		
		autocompleteTercero = new AutocompleteTercero() {

			
			@Override
			public void postSelect() {
				tercero = getSeleccionado();
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		autocompleteTercero.setQuery("");
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void crearNuevoTercero() {

		tercero.setTipo(TipoTerceroEnum.CLIENTE_MINORISTA);

		tercero=terceroService.save(tercero);

		autocompleteTercero.setSeleccionado(tercero);

		autocompleteTercero.setQuery(tercero.toString());

		runJavascript("dlgNewCliente.hide();");
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void agregarCuota() {

		cuota.setEstado(EstadoVentaItemCuotaEnum.valueOf(estadoCuota));
		if (estadoCuota.equals(EstadoCuotaEnum.PAGADA.name())) {
			cuota.setFechaPago(new Date());
		}
		
		venta.getCuotas().add(cuota);
		cuota = new VentaItemCuota();

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cuota
	 */
	public void eliminarCuota(VentaItemCuota cuota) {
		venta.setValorPagar(venta.getValorPagar()-cuota.getValor());
		venta.getCuotas().remove(cuota);
		

	}
/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 29/10/2013
 */
	public void guardarVenta() {
		venta.setValorPagar(venta.getValorPagar()-venta.getDescuento());
		venta.setCuenta(cuentasService.findById(idCuenta));
		venta.setCliente(autocompleteTercero.getSeleccionado());
		venta.setModoPago(ModoPagoEnum.valueOf(modoPago));
		
	
		if (venta.getModoPago().equals(ModoPagoEnum.CONTADO)) {
			VentaItemCuota cuota= new VentaItemCuota();
			cuota.setEstado(EstadoVentaItemCuotaEnum.PAGADA);
			cuota.setFechaCierre(new Date());
			cuota.setFechaPago(new Date());
			cuota.setIdVenta(venta);
			cuota.setValor(venta.getValorPagar());
			venta.getCuotas().add(cuota);
		}
		
		try {
			
			 vt=ventaItemEjb.registrarVenta(venta);
			init();
			login.updateValorCaja();
			ventas.add(vt);
			venta=new VentaItem();
			addAttribute(SessionParams.ENTITY_BACK, vt);
			goTo("/paginas/almacen/venta/item/respuesta.jsf");
		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/01/2014
	*/
	public void printReport(){
		
		printerBean.exportPdf("facturaIndividual_1", "factura_"+vt.getIdVenta(),null,ventas);
	}
	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void addExistencia() {
		
		if (tercero.getIdTecero()==null) {
			mensajeError("Por favor selecciona primero un cliente");
			return;
		}
		boolean existe = false;
		BodegaExistencia be = bodegaExistenciaService.findByBarcode(productoId,
				login.getTercero().getSucursalTerceroList().get(0)
						.getSucursalidSucursal());
		if (be != null) {
			existe = true;
		}
		be = bodegaExistenciaService.findByBarcodeActivo(productoId, login
				.getTercero().getSucursalTerceroList().get(0)
				.getSucursalidSucursal());
		if (be != null) {
			VentaItemDetalleItem detalle = new VentaItemDetalleItem();
			detalle.setExistencia(be);
			detalle.setIdVenta(venta);
			if (venta.getExistencias().contains(detalle)) {
				mensajeError(new StringBuilder(
						"Este Producto ya está en la lista").toString());
				return;
			}
			venta.getExistencias().add(detalle);
			/* adicionamos su precio al valor a pagar de la venta */
			if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {
				
				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getExistencia().getPrecioVentaMayoristas());
			}else{
				
				
			
			venta.setValorPagar(venta.getValorPagar()
					+ detalle.getExistencia().getPrecioVenta());
			}
			productoId = "";
		} else {
			if (existe) {

				mensajeError(new StringBuilder(
						" este PID se encuentra en otra bodega o punto de venta")
						.toString());
			} else {

				mensajeError(new StringBuilder(
						"Este PID no esta registrado en el inventario")
						.toString());
			}
		}
	}

	public void eliminarExistencia(VentaItemDetalleItem detalle) {
		venta.getExistencias().remove(detalle);

	}

	/**
	 * Metodo que inicializa una venta con los datos claves
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	private void inicializarVenta() {
		venta = new VentaItem();
		venta.setFecha(new Date());
		venta.setEstado(EstadoVentaItemEnum.ACTIVO);
		venta.setVendedor(login.getTercero());
		venta.setValorPagar(BigInteger.ZERO.doubleValue());
		venta.setObservacion("");
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the venta
	 */
	public VentaItem getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(VentaItem venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param login
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param productoId
	 *            the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param autocompleteTercero
	 *            the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the idCuenta
	 */
	public int getIdCuenta() {
		return idCuenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param idCuenta
	 *            the idCuenta to set
	 */
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the modoPago
	 */
	public String getModoPago() {
		return modoPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param modoPago
	 *            the modoPago to set
	 */
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the cuota
	 */
	public VentaItemCuota getCuota() {
		return cuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(VentaItemCuota cuota) {
		this.cuota = cuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the estadoCuota
	 */
	public String getEstadoCuota() {
		return estadoCuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param estadoCuota
	 *            the estadoCuota to set
	 */
	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the printerBean
	 */
	public PrinterBean getPrinterBean() {
		return printerBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param ventaItemEjb the ventaItemEjb to set
	 */
	public void setVentaItemEjb(VentaItemEjb ventaItemEjb) {
		this.ventaItemEjb = ventaItemEjb;
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
	 * @param terceroService the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param cuentasService the cuentasService to set
	 */
	public void setCuentasService(CuentasService cuentasService) {
		this.cuentasService = cuentasService;
	}
	
}
