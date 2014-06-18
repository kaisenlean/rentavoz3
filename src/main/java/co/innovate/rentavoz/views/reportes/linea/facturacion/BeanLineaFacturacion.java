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

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanLineaFacturacion
 * @date 26/05/2014
 * 
 */
@ManagedBean
@ViewScoped
public class BeanLineaFacturacion extends StandardAbm<VentaLinea, Integer> {

	/**
	 * 26/05/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ventaLineaService}")
	private VentaLineaService ventaLineaService;

	@ManagedProperty(value = "#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService;

	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;

	private AutocompleteTercero autocompleteTercero;

	private Tercero cliente;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	private int corte = 0;
	private String linea = "";
	private int selFechaFacturacion;
	private Date fecha;
	private Date fechaLim;
	private String modoPago;
	
	private String numeroFactura=StringUtils.EMPTY;

	private double totalPrecioCompra;
	private double totalPrecioVenta;

	private Venta venta;

	private PieChartModel modelChart = new PieChartModel();

	@ManagedProperty(value = "#{facturaControllerService}")
	private FacturaControllerService facturaControllerService;

	private CartesianChartModel categoryModel;

	public void init2() {
		selFechaFacturacion = fechaFacturacionService.findByFecha(
				Calendar.getInstance().getTime()).getId();
		fecha = Calendar.getInstance().getTime();
		fechaLim = Calendar.getInstance().getTime();

		int lineasVendidas = ventaLineaService.countdByCriterio(
				"",
				null,
				corte == 0 ? Integer.valueOf(new SimpleDateFormat("dd")
						.format(Calendar.getInstance().getTime())) : corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);
		int lineasReales = lineaService.countByCorte(corte == 0 ? Integer
				.valueOf(new SimpleDateFormat("dd").format(Calendar
						.getInstance().getTime())) : corte);

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

		totalPrecioVenta = ventaLineaService.sumByCriterio(linea, cliente,
				corte, fechaFacturacionService.findById(selFechaFacturacion),
				fecha, fechaLim, modoPago,numeroFactura);
		totalPrecioCompra = ventaLineaService.sumByCriterioCompra(linea,
				cliente, corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);

		autocompleteTercero = new AutocompleteTercero() {

			@Override
			public void postSelect() {
				cliente = seleccionado;
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};

	}

	public void onDateSelect(SelectEvent event) {
		try {

			selFechaFacturacion = fechaFacturacionService.findByFecha(
					(Date) event.getObject()).getId();
		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}

	public void loadFactura(Venta venta) {
		venta = facturaControllerService.cargarFactura(venta);
		this.venta = venta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<VentaLinea, Integer> getFacade() {
		return ventaLineaService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public VentaLinea getInstancia() {
		return new VentaLinea();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/reportes/linea/facturacion/index.jsf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String
	 * )
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {

		return ventaLineaService.countdByCriterio(linea, cliente, corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<VentaLinea> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {

		Order order = null;
		switch (sortOrder) {
		case UNSORTED:
			order = Order.asc(sortField == null ? "id" : sortField);
			break;
		case ASCENDING:
			order = Order.asc(sortField == null ? "id" : sortField);
			break;
		case DESCENDING:
			order = Order.desc(sortField == null ? "id" : sortField);
			break;

		default:
			break;
		}

		totalPrecioVenta = ventaLineaService.sumByCriterio(linea, cliente,
				corte, fechaFacturacionService.findById(selFechaFacturacion),
				fecha, fechaLim, modoPago,numeroFactura);
		totalPrecioCompra = ventaLineaService.sumByCriterioCompra(linea,
				cliente, corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);
		return ventaLineaService.findByCriterio(startingAt, maxPerPage, order,
				linea, cliente, corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public VentaLinea getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<VentaLinea> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * @param corte
	 *            the corte to set
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
	 * @param linea
	 *            the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @return the cliente
	 */
	public Tercero getCliente() {
		return cliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Tercero cliente) {
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
	 * @param selFechaFacturacion
	 *            the selFechaFacturacion to set
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
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param fechaFacturacionService
	 *            the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param ventaLineaService
	 *            the ventaLineaService to set
	 */
	public void setVentaLineaService(VentaLineaService ventaLineaService) {
		this.ventaLineaService = ventaLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param lineaService
	 *            the lineaService to set
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

		int lineasVendidas = ventaLineaService.countdByCriterio(
				"",
				null,
				corte == 0 ? Integer.valueOf(new SimpleDateFormat("dd")
						.format(Calendar.getInstance().getTime())) : corte,
				fechaFacturacionService.findById(selFechaFacturacion), fecha,
				fechaLim, modoPago,numeroFactura);
		int lineasReales = lineaService.countByCorte(corte == 0 ? Integer
				.valueOf(new SimpleDateFormat("dd").format(Calendar
						.getInstance().getTime())) : corte);

		modelChart = new PieChartModel();

		modelChart.set("Lineas en inventario", lineasReales);
		modelChart.set("Lineas Vendidas", lineasVendidas);

		return modelChart;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/05/2014
	 * @param modelChart
	 *            the modelChart to set
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
	 * @param categoryModel
	 *            the categoryModel to set
	 */
	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the totalPrecioCompra
	 */
	public double getTotalPrecioCompra() {
		return totalPrecioCompra;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param totalPrecioCompra
	 *            the totalPrecioCompra to set
	 */
	public void setTotalPrecioCompra(double totalPrecioCompra) {
		this.totalPrecioCompra = totalPrecioCompra;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the totalPrecioVenta
	 */
	public double getTotalPrecioVenta() {
		return totalPrecioVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param totalPrecioVenta
	 *            the totalPrecioVenta to set
	 */
	public void setTotalPrecioVenta(double totalPrecioVenta) {
		this.totalPrecioVenta = totalPrecioVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param facturaControllerService
	 *            the facturaControllerService to set
	 */
	public void setFacturaControllerService(
			FacturaControllerService facturaControllerService) {
		this.facturaControllerService = facturaControllerService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the fechaLim
	 */
	public Date getFechaLim() {
		return fechaLim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param fechaLim
	 *            the fechaLim to set
	 */
	public void setFechaLim(Date fechaLim) {
		this.fechaLim = fechaLim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 28/05/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 28/05/2014
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 28/05/2014
	 * @param autocompleteTercero
	 *            the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/06/2014
	 * @return the modoPago
	 */
	public String getModoPago() {
		return modoPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/06/2014
	 * @param modoPago
	 *            the modoPago to set
	 */
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/06/2014
	 * @return the numeroFactura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/06/2014
	 * @param numeroFactura the numeroFactura to set
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

}
