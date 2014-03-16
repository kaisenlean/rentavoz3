package co.innovate.rentavoz.views.venta.linea.cuota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.Login;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanConsultaCuota
 * @date 9/02/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanConsultaCuota extends BaseBean implements Serializable {


	/**
	 * 9/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	
	private AutocompleteTercero autocompleteCliente;

	
	@ManagedProperty(value="#{cuotaService}")
	private CuotaService cuotaService;
	
	@ManagedProperty(value="#{facturaControllerService}")
	private FacturaControllerService facturaControllerService;
	
	@ManagedProperty(value="#{opcionService}")
	private OpcionService opcionService;
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	private List<Cuota> lista= new ArrayList<Cuota>();
	private List<Cuota> seleccionadas= new ArrayList<Cuota>();

	protected Tercero cliente;
	
	private Cuota cuota;
	
	@PostConstruct
	public void init(){
		login.updateValorCaja();
		autocompleteCliente=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				lista=cuotaService.buscarCuotasPendientesPorCliente(seleccionado);
				cliente=seleccionado;
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		
	}
	
	
	
	public void pagarCuotasSeleccionadas(){
		
		
		runJavascript("btnPagarCuotas"+".jq.click();");
	}
	
	
	
	public void loadCuota(Cuota cuota){
		this.cuota=cuota;
		
	}
	public void pagarSeleccionadas(){
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		try {

			Opcion opcion = opcionService
					.findByClave(OpcionConstants.RAZON_SOCIAL_EMPRESA);
			if (opcion == null) {
				mapa.put("RAZON_SOCIAL", "No parametrizada");
			} else {
				mapa.put("RAZON_SOCIAL", opcion.getValor());
			}
			
			 opcion = opcionService
					.findByClave(OpcionConstants.DIRECCION_EMPRESA);
			if (opcion == null) {
				mapa.put("DIRECCION_EMPRESA", "No parametrizada");
			} else {
				mapa.put("DIRECCION_EMPRESA", opcion.getValor());
			}
			

			 opcion = opcionService
					.findByClave(OpcionConstants.NIT_EMPRESA);
			if (opcion == null) {
				mapa.put("NIT", "No parametrizada");
			} else {
				mapa.put("NIT", opcion.getValor());
			}
			
			 opcion = opcionService
						.findByClave(OpcionConstants.IMAGEN_EMPRESA);
				if (opcion == null) {
					mapa.put("IMAGEN_EMPRESA", "");
				} else {
					mapa.put("IMAGEN_EMPRESA", "/"+opcion.getValor());
				}
				
				

				 opcion = opcionService
							.findByClave(OpcionConstants.TELEFONOS_EMPRESA);
					if (opcion == null) {
						mapa.put("TELEFONO_EMPRESA", "");
					} else {
						mapa.put("TELEFONO_EMPRESA", opcion.getValor());
					}
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		int cont=0;
		List<Venta> ventas = new ArrayList<Venta>();
		for (Cuota cuota : lista) {
			if (!cuota.isSeleccionada()) {
				continue;
			}
			cont++;
		Venta venta=facturaControllerService.pagarCuota(cuota);
		venta.setValorCuota(cuota.getValorCuota().toString());

		ventas.add(venta);


		
		}
		if (cont>0) {
			
		printerBean.exportPdf("facturaIndividual_lineas_reimpresa",
				"factura_lineas_reimpresa_listado" , mapa, ventas);

		login.updateValorCaja();
		}else{
			
			mensajeError("Por favor selecciona por lo menos una cuota");
		}
		
	}
	
	
	
public void saveEditRow(){
		
		for (Cuota cuota : lista) {
			if (cuota.equals(this.cuota)) {
				cuota=this.cuota;
			}
		}
		cuota=new Cuota();
		runJavascript("dialogo.hide();");
	}
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param cuota
	*/
	public void pagarCuota(Cuota cuota){
	lista.remove(cuota);
	login.updateValorCaja();
	runJavascript("btnCuota"+(cuota.getNumero())+".jq.click();");
	
	}
	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 10/02/2014
	* @param cuota
	*/
	public void pagarCuotaImprimirFactura(Cuota cuota){
		Venta venta=facturaControllerService.pagarCuota(cuota);
		venta.setValorCuota(cuota.getValorCuota().toString());
		HashMap<String, Object> mapa = new HashMap<String, Object>();

		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);


		try {

			Opcion opcion = opcionService
					.findByClave(OpcionConstants.RAZON_SOCIAL_EMPRESA);
			if (opcion == null) {
				mapa.put("RAZON_SOCIAL", "No parametrizada");
			} else {
				mapa.put("RAZON_SOCIAL", opcion.getValor());
			}
			
			 opcion = opcionService
					.findByClave(OpcionConstants.DIRECCION_EMPRESA);
			if (opcion == null) {
				mapa.put("DIRECCION_EMPRESA", "No parametrizada");
			} else {
				mapa.put("DIRECCION_EMPRESA", opcion.getValor());
			}
			

			 opcion = opcionService
					.findByClave(OpcionConstants.NIT_EMPRESA);
			if (opcion == null) {
				mapa.put("NIT", "No parametrizada");
			} else {
				mapa.put("NIT", opcion.getValor());
			}
			
			 opcion = opcionService
						.findByClave(OpcionConstants.IMAGEN_EMPRESA);
				if (opcion == null) {
					mapa.put("IMAGEN_EMPRESA", "");
				} else {
					mapa.put("IMAGEN_EMPRESA", "/"+opcion.getValor());
				}
				
				

				 opcion = opcionService
							.findByClave(OpcionConstants.TELEFONOS_EMPRESA);
					if (opcion == null) {
						mapa.put("TELEFONO_EMPRESA", "");
					} else {
						mapa.put("TELEFONO_EMPRESA", opcion.getValor());
					}
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		
		printerBean.exportPdf("facturaIndividual_lineas_reimpresa",
				"factura_lineas_reimpresa" + venta.getIdVenta(), mapa, ventas);

		mensaje("Realizado", "Se ha efectuado el pago de la cuota seleccionada");
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param terceroService the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @return the autocompleteCliente
	 */
	public AutocompleteTercero getAutocompleteCliente() {
		return autocompleteCliente;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param autocompleteCliente the autocompleteCliente to set
	 */
	public void setAutocompleteCliente(AutocompleteTercero autocompleteCliente) {
		this.autocompleteCliente = autocompleteCliente;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param cuotaService the cuotaService to set
	 */
	public void setCuotaService(CuotaService cuotaService) {
		this.cuotaService = cuotaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @return the lista
	 */
	public List<Cuota> getLista() {
		return lista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param lista the lista to set
	 */
	public void setLista(List<Cuota> lista) {
		this.lista = lista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param facturaControllerService the facturaControllerService to set
	 */
	public void setFacturaControllerService(
			FacturaControllerService facturaControllerService) {
		this.facturaControllerService = facturaControllerService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param opcionService the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @return the seleccionadas
	 */
	public List<Cuota> getSeleccionadas() {
		return seleccionadas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param seleccionadas the seleccionadas to set
	 */
	public void setSeleccionadas(List<Cuota> seleccionadas) {
		this.seleccionadas = seleccionadas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/03/2014
	 * @return the cuota
	 */
	public Cuota getCuota() {
		return cuota;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/03/2014
	 * @param cuota the cuota to set
	 */
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}
}
