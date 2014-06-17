package co.innovate.rentavoz.views.venta.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.innovate.rentavoz.logic.venta.item.VentaItemEjb;
import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TipoTerceroEnum;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.EstadoVentaItemEnum;
import co.innovate.rentavoz.model.venta.ModoPagoEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.model.venta.VentaItemDetalleItem;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.bodegaitem.BodegaItemService;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.cuenta.CuentasService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.planpago.PlanPagoService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteCiudad;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteColaboradores;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteItem;
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
	 * 14/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         CUOTA2
	 */
	private static final String CUOTA2 = "CUOTA";
	/**
	 * 15/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         vt
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
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ventas
	 */
	List<VentaItem> ventas = new ArrayList<VentaItem>();

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
	@ManagedProperty(value = "#{ventaItemEjb}")
	private VentaItemEjb ventaItemEjb;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         venta
	 */
	private VentaItem venta;

	@ManagedProperty(value = "#{bodegaExistenciaService}")
	private BodegaExistenciaService bodegaExistenciaService;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	@ManagedProperty(value = "#{printerBean}")
	private PrinterBean printerBean;

	@ManagedProperty(value = "#{ciudadService}")
	private CiudadService ciudadService;

	@ManagedProperty(value = "#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService;

	private int planPago;

	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         productoId
	 */
	private String productoId;

	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         tercero
	 */
	private Tercero tercero;

	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         autocompleteTercero
	 */
	private AutocompleteTercero autocompleteTercero;

	/**
	 * 2/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         selFechaFacturacion
	 */
	private Integer selFechaFacturacion;
	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         idCuenta
	 */
	private int idCuenta;

	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         modoPago
	 */
	private String modoPago;

	/**
	 * 30/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         cuota
	 */
	private VentaItemCuota cuota;

	/**
	 * 30/10/2013r
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         estadoCuota
	 */
	private String estadoCuota;

	private AutocompleteCiudad autocompleteCiudad;

	@ManagedProperty(value = "#{cuentasService}")
	private CuentasService cuentasService;

	private AutocompleteColaboradores autocompleteColaboradores;
	private AutocompleteColaboradores autocompleteColaboradores2;
	private VentaItemDetalleItem item;

	private AutocompleteItem autocompleteItem;

	@ManagedProperty(value = "#{bodegaItemService}")
	private BodegaItemService bodegaItemService;

	@ManagedProperty(value = "#{sucursalTerceroService}")
	private SucursalTerceroService sucursalTerceroService;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty(value = "#{planPagoService}")
	private PlanPagoService planPagoService;

	private int idSucursal;

	private boolean ventaSinSerial = false;

	private BodegaItem bodegaItem;

	private int cantidad;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	@PostConstruct
	public void init() {

		inicializarVenta();

		tercero = new Tercero();
		tercero.setMayorista(Boolean.FALSE);
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

		autocompleteCiudad = new AutocompleteCiudad() {

			@Override
			public void postSelect() {
				tercero.setCiudad(seleccionado);
			}

			@Override
			public CiudadService getFacade() {
				return ciudadService;
			}
		};

		autocompleteColaboradores = new AutocompleteColaboradores() {

			@Override
			public void postSelect() {
				venta.setVendedor(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		autocompleteColaboradores.setQuery(login.getTercero() == null ? null
				: login.getTercero().toString());
		autocompleteColaboradores.setSeleccionado(login.getTercero());

		autocompleteColaboradores2 = new AutocompleteColaboradores() {

			@Override
			public void postSelect() {
				venta.setCobrador(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};

		autocompleteItem = new AutocompleteItem() {

			@Override
			public void postSelect() {
				bodegaItem = seleccionado;
			}

			@Override
			public BodegaItemService getFacade() {
				return bodegaItemService;
			}
		};
		autocompleteColaboradores2.setQuery(login.getTercero() == null ? null
				: login.getTercero().toString());
		autocompleteColaboradores2.setSeleccionado(login.getTercero());
		FechaFacturacion fechaFacturacion = fechaFacturacionService
				.findByFecha(new Date());
		selFechaFacturacion = fechaFacturacion == null ? 0 : fechaFacturacion
				.getId();
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void crearNuevoTercero() {

		if (tercero.isMayorista()) {
			tercero.setTipo(TipoTerceroEnum.CLIENTE_MAYORISTA);
		} else {

			tercero.setTipo(TipoTerceroEnum.CLIENTE_MINORISTA);
		}

		tercero = terceroService.save(tercero);

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

	public void cargarPlanPago() {
		PlanPago plan = planPagoService.findById(planPago);
		if (plan == null) {
			mensajeError("No se pudo cargar el plan de pago seleccionado");
			return;
		}
		modoPago = CUOTA2;
		venta.getCuotas().clear();

		double valorCuota = venta.getValorPagar() / plan.getNumeroCuotas();

		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < plan.getNumeroCuotas(); i++) {
			VentaItemCuota c = new VentaItemCuota();
			c.setEstado(EstadoVentaItemCuotaEnum.PENDIENTE);
			c.setValor(BigDecimal.valueOf(valorCuota).doubleValue());
			cal.add(Calendar.DAY_OF_YEAR, plan.getDiasDiferencia());
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_YEAR, BigInteger.ONE.intValue());
			}
			c.setFechaCierre(cal.getTime());
			venta.getCuotas().add(c);

		}

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cuota
	 */
	public void eliminarCuota(VentaItemCuota cuota) {
		venta.setValorPagar(venta.getValorPagar() - cuota.getValor());
		venta.getCuotas().remove(cuota);

	}

	public void loadItem(VentaItemDetalleItem item) {

		this.item = item;
		runJavascript("dialogo.show();");

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void guardarVenta() {
		venta.setValorPagar(venta.getValorPagar() - venta.getDescuento());
		venta.setCuenta(cuentasService.findById(idCuenta));
		venta.setCliente(autocompleteTercero.getSeleccionado());
		venta.setModoPago(ModoPagoEnum.valueOf(modoPago));
		venta.setFechaFacturacion(fechaFacturacionService
				.findById(selFechaFacturacion));
		venta.setSucursal(sucursalService.findById(idSucursal));

		if (venta.getModoPago().equals(ModoPagoEnum.CONTADO)) {
			VentaItemCuota cuota = new VentaItemCuota();
			cuota.setEstado(EstadoVentaItemCuotaEnum.PAGADA);
			cuota.setFechaCierre(new Date());
			cuota.setFechaPago(new Date());
			cuota.setIdVenta(venta);
			cuota.setValor(venta.getValorPagar());
			venta.getCuotas().add(cuota);
		}

		try {

			vt = ventaItemEjb.registrarVenta(venta);
			init();
			login.updateValorCaja();
			ventas.add(vt);
			venta = new VentaItem();
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
	public void printReport() {

		printerBean.exportPdf("facturaIndividual_1",
				"factura_" + vt.getIdVenta(), null, ventas);
	}

	public void saveEditRow() {
		boolean continua = true;
		if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {
			if (item.getValorItem() < item.getExistencia().getBodegaItemBean()
					.getPrecioVentaMayoristasMinimo()) {
				mensajeError("Este valor es inferior al precio minimo de venta establecido");
				continua = false;
				;
			}

		} else {
			if (item.getValorItem() < item.getExistencia().getBodegaItemBean()
					.getPrecioVentaMinimo()) {
				mensajeError("Este valor es inferior al precio minimo de venta establecido");
				continua = false;
				;
			}

		}

		venta.setValorPagar(BigInteger.ZERO.doubleValue());
		if (continua) {

			for (VentaItemDetalleItem it : venta.getExistencias()) {
				if (it.equals(item)) {
					it = item;
				}
			}
		}
		for (VentaItemDetalleItem detalle : venta.getExistencias()) {
			if (!continua) {

				if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {

					detalle.setValorItem(detalle.getExistencia()
							.getBodegaItemBean().getPrecioVentaMayoristas());
				} else {

					detalle.setValorItem(detalle.getExistencia()
							.getBodegaItemBean().getPrecioVenta());
				}
			}
			if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {

				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getValorItem());
			} else {

				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getValorItem());
			}
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void addExistencia() {

		if (tercero.getIdTecero() == null) {
			mensajeError("Por favor selecciona primero un cliente");
			return;
		}
		boolean existe = false;
		BodegaExistencia be = bodegaExistenciaService.findByBarcode(productoId,
				login.getSucursales());
		if (be != null) {
			existe = true;
		}
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

				detalle.setValorItem(detalle.getExistencia()
						.getBodegaItemBean().getPrecioVentaMayoristas());
				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getExistencia().getBodegaItemBean()
								.getPrecioVentaMayoristas());
			} else {
				detalle.setValorItem(detalle.getExistencia()
						.getBodegaItemBean().getPrecioVenta());
				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getExistencia().getBodegaItemBean()
								.getPrecioVenta());
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

	public void addExistencia1() {

		if (tercero.getIdTecero() == null) {
			mensajeError("Por favor selecciona primero un cliente");
			return;
		}
		List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();
		if (bodegaItem == null) {
			mensajeError("Selecciona un producto para continuar");
			return;
		}
		if (cantidad == BigInteger.ZERO.intValue()) {
			mensajeError("Digita una cantidad válida");
			return;
		}
		existencias = bodegaExistenciaService.findByDemandaPorCantidad(
				cantidad, bodegaItem);
		for (BodegaExistencia be : existencias) {
			VentaItemDetalleItem detalle = new VentaItemDetalleItem();
			detalle.setExistencia(be);
		
			detalle.setIdVenta(venta);
			if (venta.getExistencias().contains(detalle)) {
				mensajeError(new StringBuilder(
						"Este Producto ya está en la lista").toString());
				return;
			}
			
			/* adicionamos su precio al valor a pagar de la venta */
			if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {

				detalle.setValorItem(detalle.getExistencia()
						.getBodegaItemBean().getPrecioVentaMayoristas());
				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getExistencia().getBodegaItemBean()
								.getPrecioVentaMayoristas());
			} else {
				detalle.setValorItem(detalle.getExistencia()
						.getBodegaItemBean().getPrecioVenta());
				venta.setValorPagar(venta.getValorPagar()
						+ detalle.getExistencia().getBodegaItemBean()
								.getPrecioVenta());
			}
			venta.getExistencias().add(detalle);
		}

	}

	public void eliminarExistencia(VentaItemDetalleItem detalle) {
		venta.getExistencias().remove(detalle);
		if (tercero.getTipo().equals(TipoTerceroEnum.CLIENTE_MAYORISTA)) {
			venta.setValorPagar(venta.getValorPagar()
					- detalle.getExistencia().getBodegaItemBean()
							.getPrecioVentaMayoristas());
		} else {
			venta.setValorPagar(venta.getValorPagar()
					- detalle.getExistencia().getBodegaItemBean()
							.getPrecioVenta());

		}

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
		venta.setCobrador(login.getTercero());
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
	 * @param printerBean
	 *            the printerBean to set
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
	 * @param ventaItemEjb
	 *            the ventaItemEjb to set
	 */
	public void setVentaItemEjb(VentaItemEjb ventaItemEjb) {
		this.ventaItemEjb = ventaItemEjb;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param bodegaExistenciaService
	 *            the bodegaExistenciaService to set
	 */
	public void setBodegaExistenciaService(
			BodegaExistenciaService bodegaExistenciaService) {
		this.bodegaExistenciaService = bodegaExistenciaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param cuentasService
	 *            the cuentasService to set
	 */
	public void setCuentasService(CuentasService cuentasService) {
		this.cuentasService = cuentasService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param ciudadService
	 *            the ciudadService to set
	 */
	public void setCiudadService(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the ciudadService
	 */
	public CiudadService getCiudadService() {
		return ciudadService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the autocompleteCiudad
	 */
	public AutocompleteCiudad getAutocompleteCiudad() {
		return autocompleteCiudad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param autocompleteCiudad
	 *            the autocompleteCiudad to set
	 */
	public void setAutocompleteCiudad(AutocompleteCiudad autocompleteCiudad) {
		this.autocompleteCiudad = autocompleteCiudad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the autocompleteColaboradores
	 */
	public AutocompleteColaboradores getAutocompleteColaboradores() {
		return autocompleteColaboradores;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param autocompleteColaboradores
	 *            the autocompleteColaboradores to set
	 */
	public void setAutocompleteColaboradores(
			AutocompleteColaboradores autocompleteColaboradores) {
		this.autocompleteColaboradores = autocompleteColaboradores;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the autocompleteColaboradores2
	 */
	public AutocompleteColaboradores getAutocompleteColaboradores2() {
		return autocompleteColaboradores2;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param autocompleteColaboradores2
	 *            the autocompleteColaboradores2 to set
	 */
	public void setAutocompleteColaboradores2(
			AutocompleteColaboradores autocompleteColaboradores2) {
		this.autocompleteColaboradores2 = autocompleteColaboradores2;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param fechaFacturacionService
	 *            the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the selFechaFacturacion
	 */
	public Integer getSelFechaFacturacion() {
		return selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param selFechaFacturacion
	 *            the selFechaFacturacion to set
	 */
	public void setSelFechaFacturacion(Integer selFechaFacturacion) {
		this.selFechaFacturacion = selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the item
	 */
	public VentaItemDetalleItem getItem() {
		return item;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param item
	 *            the item to set
	 */
	public void setItem(VentaItemDetalleItem item) {
		this.item = item;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursalTerceroService
	 *            the sucursalTerceroService to set
	 */
	public void setSucursalTerceroService(
			SucursalTerceroService sucursalTerceroService) {
		this.sucursalTerceroService = sucursalTerceroService;
	}

	public List<SelectItem> getItemsSucursales() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (SucursalTercero st : sucursalTerceroService.findByTercero(login
				.getTercero())) {
			lista.add(new SelectItem(
					st.getSucursalidSucursal().getIdSucursal(), st
							.getSucursalidSucursal().getSucNombre()));
		}
		return lista;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param idSucursal
	 *            the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/04/2014
	 * @return the planPago
	 */
	public int getPlanPago() {
		return planPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/04/2014
	 * @param planPago
	 *            the planPago to set
	 */
	public void setPlanPago(int planPago) {
		this.planPago = planPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/04/2014
	 * @param planPagoService
	 *            the planPagoService to set
	 */
	public void setPlanPagoService(PlanPagoService planPagoService) {
		this.planPagoService = planPagoService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @return the ventaSinSerial
	 */
	public boolean isVentaSinSerial() {
		return ventaSinSerial;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param ventaSinSerial
	 *            the ventaSinSerial to set
	 */
	public void setVentaSinSerial(boolean ventaSinSerial) {
		this.ventaSinSerial = ventaSinSerial;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param bodegaItemService
	 *            the bodegaItemService to set
	 */
	public void setBodegaItemService(BodegaItemService bodegaItemService) {
		this.bodegaItemService = bodegaItemService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @return the autocompleteItem
	 */
	public AutocompleteItem getAutocompleteItem() {
		return autocompleteItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param autocompleteItem
	 *            the autocompleteItem to set
	 */
	public void setAutocompleteItem(AutocompleteItem autocompleteItem) {
		this.autocompleteItem = autocompleteItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @return the bodegaItem
	 */
	public BodegaItem getBodegaItem() {
		return bodegaItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param bodegaItem
	 *            the bodegaItem to set
	 */
	public void setBodegaItem(BodegaItem bodegaItem) {
		this.bodegaItem = bodegaItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
