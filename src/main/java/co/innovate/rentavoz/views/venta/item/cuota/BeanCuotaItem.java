/**
 * 
 */
package co.innovate.rentavoz.views.venta.item.cuota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.model.venta.EstadoVentaItemCuotaEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.services.venta.item.cuota.VentaItemCuotaService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.Login;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanCuotaItem
 * @date 15/02/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanCuotaItem extends BaseBean implements Serializable {

	/**
	 * 15/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{ventaItemCuotaService}")
	private VentaItemCuotaService ventaItemCuotaService;
	
	
	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	
	@ManagedProperty(value="#{opcionService}")
	private OpcionService opcionService;
	
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	
	@ManagedProperty(value="#{facturaControllerService}")
	private FacturaControllerService facturaControllerService;
	
	private AutocompleteTercero autocompleteTercero;
	
	
	private List<VentaItemCuota> cuotas;
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	public void pagarCuota(VentaItemCuota cuota){
		runJavascript("btnCuota".concat(cuota.getId().toString()).concat(".jq.click();"));
	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/02/2014
	* @param cuota
	*/
	public void pagarCuotaImprimirFactura(VentaItemCuota cuota){
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
		
		
		
		List<VentaItem> ventas= new ArrayList<VentaItem>();
		
		if (cuota.getAbono()==0) {
			mensajeError("El valor del abono no puede ser 0");
			return;
		}else{
			
			if (cuota.getAbono()>cuota.getValor()) {
				mensajeError("El valor de la cuota es menor al abono");
				return;
			}
			
			if (cuota.getAbono()<cuota.getValor()) {
				double saldo = cuota.getValor()-cuota.getAbono();
				VentaItemCuota ctemp= new VentaItemCuota();
				ctemp.setEstado(EstadoVentaItemCuotaEnum.PENDIENTE);
				Calendar c=Calendar.getInstance();
				c.add(Calendar.DAY_OF_YEAR, 5);
				ctemp.setFechaCierre(c.getTime());
				ctemp.setValor(saldo);
				ctemp.setIdVenta(cuota.getIdVenta());
				ventaItemCuotaService.save(ctemp);
				
				cuota.setValor(cuota.getAbono());
			}
			
		}
		
		VentaItem venta=facturaControllerService.pagarCuotaItem(cuota);
		venta.setValorAbono(cuota.getValor());
		ventas.add(venta);

		init();
		printerBean.exportPdf("facturaIndividual_1_reimpresa",
				"factura_equipos_reimpresa" + venta.getIdVenta(), mapa, ventas);

		mensaje("Realizado", "Se ha efectuado el pago de la cuota seleccionada");
	}
	
public void pagarCuotasSeleccionadas(){
		
		
		runJavascript("btnPagarCuotas"+".jq.click();");
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
		List<VentaItem> ventas = new ArrayList<VentaItem>();
		for (VentaItemCuota cuota : cuotas) {
			if (!cuota.getPagada()) {
				continue;
			}
			cont++;
		VentaItem venta=facturaControllerService.pagarCuotaItem(cuota);
		venta.setValorAbono(cuota.getValor());

		ventas.add(venta);


		
		}
		if (cont>0) {
			
		printerBean.exportPdf("facturaIndividual_1_reimpresa",
				"factura_equipos_listado" , mapa, ventas);

		login.updateValorCaja();
		}else{
			
			mensajeError("Por favor selecciona por lo menos una cuota");
		}
		
	}
	
	
	
	@PostConstruct
	public void init(){
		cuotas=new ArrayList<VentaItemCuota>();
		autocompleteTercero=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				cuotas=ventaItemCuotaService.findCuotasPendientesByCliente(seleccionado);
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
		
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/02/2014
	 * @param ventaItemCuotaService the ventaItemCuotaService to set
	 */
	public void setVentaItemCuotaService(
			VentaItemCuotaService ventaItemCuotaService) {
		this.ventaItemCuotaService = ventaItemCuotaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/02/2014
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/02/2014
	 * @param autocompleteTercero the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/02/2014
	 * @return the cuotas
	 */
	public List<VentaItemCuota> getCuotas() {
		return cuotas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/02/2014
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(List<VentaItemCuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param opcionService the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param facturaControllerService the facturaControllerService to set
	 */
	public void setFacturaControllerService(
			FacturaControllerService facturaControllerService) {
		this.facturaControllerService = facturaControllerService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param terceroService the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
