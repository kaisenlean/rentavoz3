/**
 * 
 */
package co.innovate.rentavoz.views.reportes.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.reports.PrinterBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanClientesMora
 * @date 5/03/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanConsultaClientesMora extends BaseBean implements Serializable {

	/**
	 * 5/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{cuotaService}")
	private CuotaService cuotaService;
	
	private List<Tercero> deudores= new ArrayList<Tercero>();
	
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	@ManagedProperty(value="#{facturaControllerService}")
	private FacturaControllerService facturaControllerService;
	
	private Venta factura;
	
	private double valorTotal;
	
	
	public void cargarFactura(Venta venta){
		
		factura=venta;
		factura=facturaControllerService.cargarFactura(factura);
	}

	
	
	
	public void consultar(){
		deudores=cuotaService.findDeudoresMorosos(Calendar.getInstance().getTime());
		for (Tercero tercero : deudores) {
			valorTotal+=tercero.getValorCuotasMora();
		}
	}
	
	public void imprimir(){
		printerBean.exportPdf("deudores_morosos", "deurores_morosos",new HashMap<String, Object>(),deudores);
		
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/03/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/03/2014
	 * @param cuotaService the cuotaService to set
	 */
	public void setCuotaService(CuotaService cuotaService) {
		this.cuotaService = cuotaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @return the deudores
	 */
	public List<Tercero> getDeudores() {
		return deudores;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @param deudores the deudores to set
	 */
	public void setDeudores(List<Tercero> deudores) {
		this.deudores = deudores;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @return the factura
	 */
	public Venta getFactura() {
		return factura;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @param factura the factura to set
	 */
	public void setFactura(Venta factura) {
		this.factura = factura;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @param facturaControllerService the facturaControllerService to set
	 */
	public void setFacturaControllerService(
			FacturaControllerService facturaControllerService) {
		this.facturaControllerService = facturaControllerService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/03/2014
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
