/**
 * 
 */
package co.innovate.rentavoz.views.reportes.linea.facturacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.criterion.Order;
import org.primefaces.model.SortOrder;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanLineaFacturacion
 * @date 26/05/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanLineaFacturacion extends StandardAbm<VentaLinea, Integer>  {

	/**
	 * 26/05/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{ventaLineaService}")
	private VentaLineaService ventaLineaService;
	
	@ManagedProperty(value="#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService; 
	
	
	@ManagedProperty(value="#{lineaService}")
	private LineaService lineaService;
	
	private int corte=0;
	private String linea="";
	private String cliente="";
	private int selFechaFacturacion;
	private Date fecha;


	private PieChartModel modelChart=new PieChartModel();


	private CartesianChartModel categoryModel;
	
	
	
	public void init2(){
		selFechaFacturacion=fechaFacturacionService.findByFecha(Calendar.getInstance().getTime()).getId();
		fecha=Calendar.getInstance().getTime();
		
		int lineasVendidas = ventaLineaService.countdByCriterio("", "", corte==0?Integer.valueOf(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime())):corte, fechaFacturacionService.findById(selFechaFacturacion), fecha);
		int lineasReales=lineaService.countByCorte( corte==0?Integer.valueOf(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime())):corte);
		
		  modelChart = new PieChartModel();
		  modelChart.set("Lineas en inventario", lineasReales);
		  modelChart.set("Lineas Vendidas", lineasVendidas);
		  
		    categoryModel = new CartesianChartModel();  
		    
	        ChartSeries inventariada = new ChartSeries();  
	        inventariada.setLabel("Inventario");  
	  
	        inventariada.set("Lineas en inventario", lineasReales);
	  
	        ChartSeries vendidas = new ChartSeries();  
	        vendidas.setLabel("Vendidas");  
	  
	        vendidas.set("Lineas Vendidas", lineasVendidas);  
	  
	        categoryModel.addSeries(inventariada);  
	        categoryModel.addSeries(vendidas); 
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<VentaLinea, Integer> getFacade() {
		return ventaLineaService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public VentaLinea getInstancia() {
		return new VentaLinea();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/reportes/linea/facturacion/index.jsf";
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return ventaLineaService.countdByCriterio(linea, cliente, corte, fechaFacturacionService.findById(selFechaFacturacion), fecha);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<VentaLinea> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		
		Order order = null;
		switch (sortOrder) {
		case UNSORTED:
			order=Order.asc(sortField==null?"id":sortField);
			break;
		case ASCENDING:
			order=Order.asc(sortField==null?"id":sortField);
			break;
		case DESCENDING:
			order=Order.desc(sortField==null?"id":sortField);
			break;

		default:
			break;
		}
		return ventaLineaService.findByCriterio(startingAt, maxPerPage, order, linea, cliente, corte, fechaFacturacionService.findById(selFechaFacturacion), fecha);
//		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public VentaLinea getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<VentaLinea> getListado() {
		return obtenerListado();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		init2();
	
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the corte
	 */
	public int getCorte() {
		return corte;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param corte the corte to set
	 */
	public void setCorte(int corte) {
		this.corte = corte;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param linea the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the selFechaFacturacion
	 */
	public int getSelFechaFacturacion() {
		return selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param selFechaFacturacion the selFechaFacturacion to set
	 */
	public void setSelFechaFacturacion(int selFechaFacturacion) {
		this.selFechaFacturacion = selFechaFacturacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param fechaFacturacionService the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @param ventaLineaService the ventaLineaService to set
 */
public void setVentaLineaService(VentaLineaService ventaLineaService) {
	this.ventaLineaService = ventaLineaService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @param lineaService the lineaService to set
 */
public void setLineaService(LineaService lineaService) {
	this.lineaService = lineaService;
}
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @return the modelChart
 */
public PieChartModel getModelChart() {

	int lineasVendidas = ventaLineaService.countdByCriterio("", "", corte==0?Integer.valueOf(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime())):corte, fechaFacturacionService.findById(selFechaFacturacion), fecha);
	int lineasReales=lineaService.countByCorte( corte==0?Integer.valueOf(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime())):corte);
	
	  modelChart = new PieChartModel();
     
	  modelChart.set("Lineas en inventario", lineasReales);
	  modelChart.set("Lineas Vendidas", lineasVendidas);
       
      
	return modelChart;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @param modelChart the modelChart to set
 */
public void setModelChart(PieChartModel modelChart) {
	this.modelChart = modelChart;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @return the categoryModel
 */
public CartesianChartModel getCategoryModel() {
	return categoryModel;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 26/05/2014
 * @param categoryModel the categoryModel to set
 */
public void setCategoryModel(CartesianChartModel categoryModel) {
	this.categoryModel = categoryModel;
}

}
