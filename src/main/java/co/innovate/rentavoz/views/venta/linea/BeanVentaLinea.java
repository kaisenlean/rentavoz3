/**
 * 
 */
package co.innovate.rentavoz.views.venta.linea;

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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.venta.linea.VentaControllerService;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TipoTerceroEnum;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.EstadoCuotaEnum;
import co.innovate.rentavoz.model.almacen.EstadoVentaEnum;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.ModalidaVentaEnum;
import co.innovate.rentavoz.model.almacen.venta.EstadoDevolucionEnum;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.model.venta.ModoPagoEnum;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.cuenta.CuentasService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.planpago.PlanPagoService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteCiudad;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteColaboradores;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.session.Login;
import co.innovate.rentavoz.views.session.OpcionConstants;


/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BeanVentaLinea
* @date 7/02/2014
*
*/
@ManagedBean
@ViewScoped
public class BeanVentaLinea extends BaseBean implements Serializable {

	/**
	 * 11/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * CUOTA
	 */
	private static final String CUOTA = "CUOTA";

	/**
	 * 5/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ventaLineaService}")
	private VentaLineaService ventaLineaService;

	@ManagedProperty(value = "#{ventaControllerService}")
	private VentaControllerService ventaControllerService;

	@ManagedProperty(value = "#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService;

	@ManagedProperty(value = "#{login}")
	private Login login;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;

	@ManagedProperty(value = "#{opcionService}")
	private OpcionService opcionService;
	
	@ManagedProperty(value="#{ciudadService}")
	private CiudadService ciudadService;
	
	@ManagedProperty(value="#{sucursalTerceroService}")
	private SucursalTerceroService sucursalTerceroService;
	
	@ManagedProperty(value="#{planPagoService}")
	private PlanPagoService planPagoService;
	
	private String modoPago;
	
	private Venta venta;

	private Integer selFechaFacturacion;
	private Integer selCuota;

	private AutocompleteColaboradores autocompleteCobrador;
	private AutocompleteColaboradores autocompleteVendedor;
	private AutocompleteTercero autocompleteTercero;
	private AutocompleteCiudad autocompleteCiudad;

	private Cuota cuota = new Cuota();
	private String estadoCuota;

	private String selEstadoCuota;
	private String numeroLinea;
	private Tercero tercero=new Tercero();

	private VentaLinea ventaItem=new VentaLinea();
	@ManagedProperty(value="#{cuentasService}")
	private CuentasService cuentasService;
	private Logger logger= Logger.getLogger(BeanVentaLinea.class);
	
	private int idSucursal;
	
	private int planPago;
	

	@ManagedProperty(value="#{sucursalService}")
	private SucursalService sucursalService;
	


	@PostConstruct
	public void init() {
		venta = new Venta();
		tercero.setMayorista(false);
		cuota.setFechaPago(new Date());
		autocompleteCobrador = new AutocompleteColaboradores() {

			@Override
			public void postSelect() {
				venta.setCobrador(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};

		autocompleteVendedor = new AutocompleteColaboradores() {

			@Override
			public void postSelect() {
				venta.setVendedor(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		
		autocompleteTercero=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				venta.setTercero(seleccionado);
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		autocompleteCiudad=new AutocompleteCiudad() {
			
			@Override
			public void postSelect() {
				tercero.setCiudad(seleccionado);
			}
			
			@Override
			public CiudadService getFacade() {
				return ciudadService;
			}
		};
		inicializarValoresVenta();
		login.updateValorCaja();
	}
	
	
	
	public void cargarPlanPago(){
		PlanPago plan = planPagoService.findById(planPago);
		if (plan==null) {
			mensajeError("No se pudo cargar el plan de pago seleccionado");
			return;
		}
		modoPago=CUOTA;
		venta.getCuotas().clear();
		
		double valorCuota = venta.getVenSaldo().doubleValue()/plan.getNumeroCuotas();
		
		Calendar cal= Calendar.getInstance();
		for (int i = 0; i < plan.getNumeroCuotas(); i++) {
			Cuota c= new Cuota();
			c.setEstadoCuota(EstadoCuotaEnum.PENDIENTE);
			c.setValorCuota(BigDecimal.valueOf(valorCuota));
			cal.add(Calendar.DAY_OF_YEAR, plan.getDiasDiferencia());
			if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_YEAR, BigInteger.ONE.intValue());
			}
			c.setFechaPago(cal.getTime());
			venta.getCuotas().add(c);
			
		}
		
		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 */
	private void inicializarValoresVenta() {
		venta.setVenFecha(new Date());
		venta.setModalidadVenta(ModalidaVentaEnum.VENTA);
		venta.setEstadoVenta(EstadoVentaEnum.ACTIVA);
		venta.setFechaFacturacion(fechaFacturacionService
				.findByFecha(new Date()));
		selFechaFacturacion = venta.getFechaFacturacion() == null ? BigInteger.ZERO
				.intValue() : venta.getFechaFacturacion().getId();
		venta.setCobrador(login.getTercero());
		venta.setVendedor(login.getTercero());
		venta.setVenSaldo(BigDecimal.valueOf(BigInteger.ZERO.doubleValue()));

		autocompleteCobrador.setSeleccionado(venta.getCobrador());
		autocompleteCobrador.setQuery(venta.getCobrador()==null?StringUtils.EMPTY:venta.getCobrador().toString());

		autocompleteVendedor.setSeleccionado(venta.getVendedor());
		autocompleteVendedor.setQuery(venta.getVendedor()==null?StringUtils.EMPTY:venta.getVendedor().toString());

	}

	
	private void calcularPrecioVenta(){
		Double valorTotal= 0.0;
		for (VentaLinea vl : venta.getVentaLineaList()) {
			valorTotal+=vl.getVentLinPrecio().doubleValue();
			valorTotal+=vl.getVentLinDeposito().doubleValue();
		}
		venta.setVenSaldo(BigDecimal.valueOf(valorTotal));
		
	}
	
	public void guardarVenta(){
		if (selFechaFacturacion==0) {
			mensajeError("Selecciona un periodo de facturación");
			return;
		}
		if (selCuota==0) {
			mensajeError("Selecciona una cuenta");
			return;
		}
		venta.setModoPago(ModoPagoEnum.valueOf(modoPago));
		switch (ModoPagoEnum.valueOf(modoPago)) {
		case CONTADO:
			Cuota cuota=new Cuota();
			cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
			cuota.setFechaPago(new Date());
			cuota.setValorCuota(venta.getVenSaldo());
			venta.getCuotas().add(cuota);
			break;
		default:
			break;

		}
		
		try {
			venta.setCuenta(cuentasService.findById(selCuota));
			venta.setSucursal(sucursalService.findById(idSucursal));
			venta.setFechaFacturacion(fechaFacturacionService.findById(selFechaFacturacion));
		venta=ventaControllerService.guardarVentaLinea(venta);
		addAttribute(SessionParams.ENTITY_BACK, venta);
		login.updateValorCaja();
		goTo("/paginas/almacen/venta/linea/respuesta.jsf");
		} catch (Exception e) {
			logger.error(e);
			mensajeError(e.toString());
			return;
		}
		
		
		
		
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
		venta.setTercero(tercero);
		runJavascript("dlgNewCliente.hide();");
	}
	
	

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 */
	public void buscarLinea() {
		try {
		if (venta.getTercero()==null) {
			mensajeError("Por favor selecciona un cliente para continuar");
			return;
		}
		
		Linea linea;
			linea = lineaService.findByNumeroObjeto(numeroLinea,login.getSucursales(),fechaFacturacionService.findById(selFechaFacturacion));
		if (linea == null) {
			mensajeError("No se ha encontrado la linea ".concat(numeroLinea));
			return;
	
		}
		

		
		VentaLinea ventaLinea = new VentaLinea();
		ventaLinea.setEstadoDevolucion(EstadoDevolucionEnum.PENDIENTE);
		ventaLinea.setLineaidLinea(linea);
		ventaLinea.setVentLinPrecio(BigDecimal.valueOf(linea.getPlan()
				.getValorPlan()));
		Opcion valorDeposito = null;
	
			valorDeposito = opcionService
					.findByClave(OpcionConstants.VALOR_DEPOSITO_LINEA);

		
		ventaLinea.setVentLinDeposito(BigDecimal
				.valueOf(valorDeposito == null ? BigInteger.ZERO.doubleValue()
						: Double.valueOf(valorDeposito.getValor())));
		if (venta.getVentaLineaList().contains(ventaLinea)) {
			mensajeError("Esta linea ya se encuentra en la lista");
			return;
		}
		venta.getVentaLineaList().add(ventaLinea);
		numeroLinea=StringUtils.EMPTY;
		calcularPrecioVenta();
		} catch (BaseException e1) {
			mensajeError(e1.toString());
		}

	}

	
	public void deleteLinea(VentaLinea linea){
		venta.getVentaLineaList().remove(linea);
		calcularPrecioVenta();
		
	}
	
	public void loadItem(VentaLinea ventaItem){
		this.ventaItem=ventaItem;
		runJavascript("dialogo.show();");
		
		
	}
	public void loadItem2(VentaLinea ventaItem){
		this.ventaItem=ventaItem;
		runJavascript("dialogo2.show();");
		
		
	}
	
	public void saveEditRow(){
		
		for (VentaLinea vl : venta.getVentaLineaList()) {
			if (vl.equals(ventaItem)) {
				vl=ventaItem;
			}
		}
		ventaItem=new VentaLinea();
		calcularPrecioVenta();
		runJavascript("dialogo.hide();");
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 */
	public void addCuota() {
		cuota.setEstadoCuota(EstadoCuotaEnum.valueOf(selEstadoCuota));
		venta.getCuotas().add(cuota);
		cuota = new Cuota();
		cuota.setFechaPago(new Date());
		

	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 7/02/2014
	* @param cuota
	*/
	public void eliminarCuota(Cuota cuota){
		venta.getCuotas().remove(cuota);
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cuota
	 */
	public void deleteCuota(Cuota cuota) {

		venta.getCuotas().remove(cuota);
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param ventaLineaService
	 *            the ventaLineaService to set
	 */
	public void setVentaLineaService(VentaLineaService ventaLineaService) {
		this.ventaLineaService = ventaLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param ventaControllerService
	 *            the ventaControllerService to set
	 */
	public void setVentaControllerService(
			VentaControllerService ventaControllerService) {
		this.ventaControllerService = ventaControllerService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param fechaFacturacionService
	 *            the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the selFechaFacturacion
	 */
	public Integer getSelFechaFacturacion() {
		return selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param selFechaFacturacion
	 *            the selFechaFacturacion to set
	 */
	public void setSelFechaFacturacion(Integer selFechaFacturacion) {
		this.selFechaFacturacion = selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the selCuota
	 */
	public Integer getSelCuota() {
		return selCuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param selCuota
	 *            the selCuota to set
	 */
	public void setSelCuota(Integer selCuota) {
		this.selCuota = selCuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the autocompleteCobrador
	 */
	public AutocompleteColaboradores getAutocompleteCobrador() {
		return autocompleteCobrador;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the autocompleteVendedor
	 */
	public AutocompleteColaboradores getAutocompleteVendedor() {
		return autocompleteVendedor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param autocompleteCobrador
	 *            the autocompleteCobrador to set
	 */
	public void setAutocompleteCobrador(
			AutocompleteColaboradores autocompleteCobrador) {
		this.autocompleteCobrador = autocompleteCobrador;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param autocompleteVendedor
	 *            the autocompleteVendedor to set
	 */
	public void setAutocompleteVendedor(
			AutocompleteColaboradores autocompleteVendedor) {
		this.autocompleteVendedor = autocompleteVendedor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param lineaService
	 *            the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the numeroLinea
	 */
	public String getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param numeroLinea
	 *            the numeroLinea to set
	 */
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param opcionService
	 *            the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param autocompleteTercero the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param ciudadService the ciudadService to set
	 */
	public void setCiudadService(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the autocompleteCiudad
	 */
	public AutocompleteCiudad getAutocompleteCiudad() {
		return autocompleteCiudad;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param autocompleteCiudad the autocompleteCiudad to set
	 */
	public void setAutocompleteCiudad(AutocompleteCiudad autocompleteCiudad) {
		this.autocompleteCiudad = autocompleteCiudad;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the modoPago
	 */
	public String getModoPago() {
		return modoPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param modoPago the modoPago to set
	 */
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the cuota
	 */
	public Cuota getCuota() {
		return cuota;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cuota the cuota to set
	 */
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the estadoCuota
	 */
	public String getEstadoCuota() {
		return estadoCuota;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param estadoCuota the estadoCuota to set
	 */
	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the ventaItem
	 */
	public VentaLinea getVentaItem() {
		return ventaItem;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param ventaItem the ventaItem to set
	 */
	public void setVentaItem(VentaLinea ventaItem) {
		this.ventaItem = ventaItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the selEstadoCuota
	 */
	public String getSelEstadoCuota() {
		return selEstadoCuota;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param selEstadoCuota the selEstadoCuota to set
	 */
	public void setSelEstadoCuota(String selEstadoCuota) {
		this.selEstadoCuota = selEstadoCuota;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cuentasService the cuentasService to set
	 */
	public void setCuentasService(CuentasService cuentasService) {
		this.cuentasService = cuentasService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursalTerceroService the sucursalTerceroService to set
	 */
	public void setSucursalTerceroService(
			SucursalTerceroService sucursalTerceroService) {
		this.sucursalTerceroService = sucursalTerceroService;
	}
	
	public List<SelectItem> getItemsSucursales(){
		List<SelectItem> lista=new ArrayList<SelectItem>();
		for (SucursalTercero st : sucursalTerceroService.findByTercero(login.getTercero())) {
			lista.add(new SelectItem(st.getSucursalidSucursal().getIdSucursal(),st.getSucursalidSucursal().getSucNombre()));
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
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param sucursalService the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/04/2014
	 * @return the planPago
	 */
	public int getPlanPago() {
		return planPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/04/2014
	 * @param planPago the planPago to set
	 */
	public void setPlanPago(int planPago) {
		this.planPago = planPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/04/2014
	 * @param planPagoService the planPagoService to set
	 */
	public void setPlanPagoService(PlanPagoService planPagoService) {
		this.planPagoService = planPagoService;
	}
	

}
