/**
 * 
 */
package co.innovate.rentavoz.views.reportes.caja;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.reportes.caja.ReporteCajaService;
import co.innovate.rentavoz.services.reportes.caja.dto.ReporteCajaDto;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.Login;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanReportesCaja
 * @date 27/02/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanReportesCaja extends BaseBean implements Serializable {

	/**
	 * 1/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FORMATO_FECHA
	 */
	private static final String FORMATO_FECHA = "dd-MM-yyyy";

	/**
	 * 27/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{reporteCajaService}")
	private ReporteCajaService reporteCajaService;
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	@ManagedProperty(value="#{sucursalService}")
	private SucursalService sucursalService;
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	@ManagedProperty(value="#{opcionService}")
	private OpcionService opcionService;
	
	private int codSucursal;
	
	private double valorCaja;
	
	private List<ReporteCajaDto> lista;
	
	private Date fechaCierre;
	
	@PostConstruct
	public void init(){
		codSucursal=BigInteger.ZERO.intValue();		
		fechaCierre=Calendar.getInstance().getTime();
		lista=new ArrayList<ReporteCajaDto>();
	}
	
	public void consultarCajas(){
		if (codSucursal==0) {
			lista=new ArrayList<ReporteCajaDto>();
			for (Sucursal	 suc : login.getSucursales()) {
				lista.addAll(reporteCajaService.reporteCajasBySucursal(suc, fechaCierre));
			}
			valorCaja=BigInteger.ZERO.doubleValue();
			for (ReporteCajaDto dto : lista) {
				valorCaja+=dto.getTotal();
			}
			return;
		}
		Sucursal sucursal = sucursalService.findById(codSucursal);
		
		if (sucursal==null) {
			mensajeError("Sucursal no encontrada");
			return;
		}
		lista=reporteCajaService.reporteCajasBySucursal(sucursal, fechaCierre);
		
	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/03/2014
	*/
	public void imprimir(){
		HashMap<String, Object> mapa= new HashMap<String, Object>();
		mapa.put("FECHA_CIERRE", fechaCierre);
		mapa.put("IMAGEN", "/".concat(opcionService.findById(OpcionConstants.IMAGEN_EMPRESA).getValor()));
		printerBean.exportPdf("cierre_caja", "cierre_caja_".concat(new SimpleDateFormat(FORMATO_FECHA).format(fechaCierre)),mapa,lista);
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the codSucursal
	 */
	public int getCodSucursal() {
		return codSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param codSucursal the codSucursal to set
	 */
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param reporteCajaService the reporteCajaService to set
	 */
	public void setReporteCajaService(ReporteCajaService reporteCajaService) {
		this.reporteCajaService = reporteCajaService;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
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
	 * @date 27/02/2014
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the valorCaja
	 */
	public double getValorCaja() {
		return valorCaja;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param valorCaja the valorCaja to set
	 */
	public void setValorCaja(double valorCaja) {
		this.valorCaja = valorCaja;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @return the lista
	 */
	public List<ReporteCajaDto> getLista() {
		return lista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/02/2014
	 * @param lista the lista to set
	 */
	public void setLista(List<ReporteCajaDto> lista) {
		this.lista = lista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/03/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/03/2014
	 * @param opcionService the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
}
