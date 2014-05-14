package co.innovate.rentavoz.views.bodega.salida;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaSalida;
import co.innovate.rentavoz.model.bodega.BodegaSalidaReferencia;
import co.innovate.rentavoz.model.bodega.EstadoExistenciaEnum;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.bodegasalida.BodegaSalidaService;
import co.innovate.rentavoz.services.bodegasalida.referencia.BodegaSalidaReferenciaService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanSalidaItem
 * @date 6/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanSalidaItem extends StandardAbm<BodegaSalida, Integer>
		implements Serializable {

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/salida/index.jsf";

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{bodegaSalidaService}")
	private BodegaSalidaService bodegaSalidaService;

	@ManagedProperty(value = "#{bodegaExistenciaService}")
	private BodegaExistenciaService bodegaExistenciaService;

	@ManagedProperty(value = "#{printerBean}")
	private PrinterBean printerBean;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty(value = "#{bodegaSalidaReferenciaService}")
	private BodegaSalidaReferenciaService bodegaSalidaReferenciaService;

	private List<BodegaSalidaReferencia> salidaReferencias = new ArrayList<BodegaSalidaReferencia>();

	private int sucursalOrigen = 0;
	private int sucursalDestino = 0;

	private String productoId;

	@ManagedProperty(value = "#{login}")
	private Login login;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<BodegaSalida, Integer> getFacade() {
		return bodegaSalidaService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaSalida getInstancia() {
		return new BodegaSalida();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public BodegaSalida getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaSalida> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		sucursalOrigen = login.getSucursal() == null ? 0 : login.getSucursal()
				.getIdSucursal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setFechaSalida(new Date());
	}

	public void addExistencia() {
		boolean existe = false;
		BodegaExistencia be = bodegaExistenciaService.findByBarcode(productoId,
				login.getSucursales());
		if (be != null) {
			existe = true;
		}
		be = bodegaExistenciaService.findByBarcodeActivo(productoId,
				login.getSucursales());
		if (be != null) {
			BodegaSalidaReferencia bodegaSalidaReferencia = new BodegaSalidaReferencia();
			bodegaSalidaReferencia.setBodegaExistencia(be);
			bodegaSalidaReferencia.setBodegaSalida(getObjeto());
			if (getObjeto().getBodegaSalidaReferencias().contains(
					bodegaSalidaReferencia)) {
				mensajeError(new StringBuilder("Este PID ya está en la lista")
						.toString());
				return;
			}
			getObjeto().getBodegaSalidaReferencias()
					.add(bodegaSalidaReferencia);

			productoId = "";
		} else {
			if (existe) {

				mensajeError(new StringBuilder(
						"Ya se ha realizado una salida de inventario con este PID a otro bodega o punto de venta")
						.toString());
			} else {

				mensajeError(new StringBuilder(
						"Este PID no esta registrado en el inventario")
						.toString());
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {

		salidaReferencias = getObjeto().getBodegaSalidaReferencias();

		if (sucursalOrigen == 0) {
			mensajeError("Selecciona una sucursal de origen");
			return false;
		}

		getObjeto().setSucursalOrigen(sucursalService.findById(sucursalOrigen));

		if (sucursalDestino == 0) {
			mensajeError("Selecciona una sucursal de destino");
			return false;
		}
		getObjeto().setSucursalDestino(
				sucursalService.findById(sucursalDestino));

		if (sucursalOrigen == sucursalDestino) {
			mensajeError("La sucursal de destino no puede ser la misma de origen");

			return false;
		}

		return true;

	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {

		if (!isEdit()) {
			generarNumeroFactura();
			for (BodegaSalidaReferencia sr : salidaReferencias) {
				BodegaExistencia be = sr.getBodegaExistencia();
				be.setEstado(EstadoExistenciaEnum.DISPONIBLE);
				be.setSucursal(getObjeto().getSucursalDestino());
				bodegaExistenciaService.save(be);

			}

			for (BodegaSalidaReferencia referencia : salidaReferencias) {
				referencia.setBodegaSalida(getObjeto());
				bodegaSalidaReferenciaService.save(referencia);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {

		getObjeto().setBodegaSalidaReferencias(
				bodegaSalidaReferenciaService.findByBodegaSalida(getObjeto()));
		sucursalDestino = getObjeto().getSucursalDestino().getIdSucursal();
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the sucursalDestino
	 */
	public int getSucursalDestino() {
		return sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the sucursalOrigen
	 */
	public int getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param sucursalDestino
	 *            the sucursalDestino to set
	 */
	public void setSucursalDestino(int sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param sucursalOrigen
	 *            the sucursalOrigen to set
	 */
	public void setSucursalOrigen(int sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param productoId
	 *            the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	/**
	 * Método que genera el numero de la factura de compra
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 */
	private void generarNumeroFactura() {
		Date now = new Date();

		StringBuilder builder = new StringBuilder();
		builder.append(new SimpleDateFormat("yyyy").format(now));
		builder.append(new SimpleDateFormat("MM").format(now));
		builder.append(new SimpleDateFormat("dd").format(now));

		builder.append("-");
		builder.append(getObjeto().getId());
		getObjeto().setNroFactura(builder.toString());
		getFacade().save(getObjeto());

	}

	public void imprimir() {
		ArrayList<BodegaSalida> salidas = new ArrayList<BodegaSalida>();
		salidas.add(getObjeto());
		printerBean.exportPdf("reporteSalidas", "reporte_salida_existencias",
				new HashMap<String, Object>(), salidas);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#postEliminar()
	 */
	@Override
	public void postEliminar() {

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/10/2013
	 * @param printerBean
	 *            the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the bodegaSalidaService
	 */
	public BodegaSalidaService getBodegaSalidaService() {
		return bodegaSalidaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param bodegaSalidaService
	 *            the bodegaSalidaService to set
	 */
	public void setBodegaSalidaService(BodegaSalidaService bodegaSalidaService) {
		this.bodegaSalidaService = bodegaSalidaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the bodegaExistenciaService
	 */
	public BodegaExistenciaService getBodegaExistenciaService() {
		return bodegaExistenciaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param bodegaExistenciaService
	 *            the bodegaExistenciaService to set
	 */
	public void setBodegaExistenciaService(
			BodegaExistenciaService bodegaExistenciaService) {
		this.bodegaExistenciaService = bodegaExistenciaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the sucursalService
	 */
	public SucursalService getSucursalService() {
		return sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the printerBean
	 */
	public PrinterBean getPrinterBean() {
		return printerBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param bodegaSalidaReferenciaService
	 *            the bodegaSalidaReferenciaService to set
	 */
	public void setBodegaSalidaReferenciaService(
			BodegaSalidaReferenciaService bodegaSalidaReferenciaService) {
		this.bodegaSalidaReferenciaService = bodegaSalidaReferenciaService;
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String)
	 */
	@Override
	public List<BodegaSalida> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

}
