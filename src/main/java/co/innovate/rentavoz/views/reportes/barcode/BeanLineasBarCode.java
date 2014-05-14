/**
 * 
 */
package co.innovate.rentavoz.views.reportes.barcode;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanLineasBarCode
 * @date 13/05/2014
 *
 */
@ManagedBean
@SessionScoped
public class BeanLineasBarCode extends StandardAbm<Linea, Integer> implements Serializable{

	/**
	 * 13/05/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{lineaService}")
	private LineaService lineaService;
	
	@ManagedProperty(value="#{opcionService}")
	private OpcionService opcionService;
	
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	private String globalFilter;
	
	private String path;

	
	
	public void imprimir(){
		HashMap<String, Object> mapa=new HashMap<String, Object>();
		mapa.put("CORTE", Integer.valueOf(globalFilter.equals(StringUtils.EMPTY)?BigInteger.ZERO.toString():globalFilter));
		printerBean.exportPdf("lineas_bar_code", "relacion_lineas_codigos_de_barra",mapa);
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/05/2014
	 * @param lineaService the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}


	





	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Linea, Integer> getFacade() {
		return lineaService;
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public Linea getInstancia() {
		return new Linea();
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/reportes/barcodelinea/index.jsf";
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return lineaService.countByCorte(Integer.valueOf(globalFilter.equals(StringUtils.EMPTY)?BigInteger.ZERO.toString():globalFilter));
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<Linea> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		
		this.globalFilter=globalFilter;
		Order order=null;
		switch (sortOrder) {
		case UNSORTED:
			order=Order.asc(sortField==null?"linNumero":sortField);
			break;

			case ASCENDING : 
				order=Order.asc(sortField==null?"linNumero":sortField);
				break;
				
				case DESCENDING :
					order=Order.desc(sortField==null?"linNumero":sortField);
					break;
		default:
			break;
		}
		
		return lineaService.findByCorte(startingAt, maxPerPage, Integer.valueOf(globalFilter.equals(StringUtils.EMPTY)?BigInteger.ZERO.toString():globalFilter), order);
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public Linea getObjeto() {
		return obtenerObjeto();
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<Linea> getListado() {
		return obtenerListado();
	}







	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
	try {
		
		path = opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor();
	} catch (Exception e) {
		mensajeError(e.toString());
	}
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/05/2014
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/05/2014
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/05/2014
	 * @param opcionService the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/05/2014
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/05/2014
	 * @return the globalFilter
	 */
	public String getGlobalFilter() {
		return globalFilter;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/05/2014
	 * @param globalFilter the globalFilter to set
	 */
	public void setGlobalFilter(String globalFilter) {
		this.globalFilter = globalFilter;
	}

}
