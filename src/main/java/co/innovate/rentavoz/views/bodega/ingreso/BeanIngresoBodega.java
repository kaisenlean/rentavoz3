package co.innovate.rentavoz.views.bodega.ingreso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.bodega.BodegaExistencia;
import co.innovate.rentavoz.model.bodega.BodegaIngreso;
import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.model.bodega.EstadoExistenciaEnum;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.bodegaexistencia.BodegaExistenciaService;
import co.innovate.rentavoz.services.bodegaingreso.BodegaIngresoService;
import co.innovate.rentavoz.services.bodegaitem.BodegaItemService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTerceroProveedor;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanIngresoBodega
 * @date 6/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanIngresoBodega extends StandardAbm<BodegaIngreso, Integer>
		implements Serializable {

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/ingreso/index.jsf";

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 3204121622329092358L;

	private String productoId;

	private AutocompleteTerceroProveedor autocompleteProveedor;

	@ManagedProperty("#{terceroService}")
	private TerceroService terceroService;

	@ManagedProperty("#{bodegaIngresoService}")
	private BodegaIngresoService bodegaIngresoService;

	@ManagedProperty("#{bodegaItemService}")
	private BodegaItemService bodegaItemService;

	@ManagedProperty("#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty("#{bodegaExistenciaService}")
	private BodegaExistenciaService bodegaExistenciaService;

	@ManagedProperty(value = "#{login}")
	private Login login;

	private int selItem = 0;
	private int selSucursal = 0;

	private BodegaItem item;
	private BodegaExistencia existemp = new BodegaExistencia();

	private List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getService()
	 */
	@Override
	public GenericService<BodegaIngreso, Integer> getFacade() {
		return bodegaIngresoService;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaIngreso getInstancia() {
		return new BodegaIngreso();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public BodegaIngreso getObjeto() {
		return obtenerObjeto();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaIngreso> getListado() {
		return obtenerListado();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		autocompleteProveedor = new AutocompleteTerceroProveedor() {

			@Override
			public void postSelect() {
				getObjeto().setProveedor(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}

		};
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the autocompleteProveedor
	 */
	public AutocompleteTerceroProveedor getAutocompleteProveedor() {
		return autocompleteProveedor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param autocompleteProveedor
	 *            the autocompleteProveedor to set
	 */
	public void setAutocompleteProveedor(
			AutocompleteTerceroProveedor autocompleteProveedor) {
		this.autocompleteProveedor = autocompleteProveedor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		autocompleteProveedor.setQuery(getObjeto().getProveedor().toString());
		if (getObjeto().getSucursal() != null) {
			selSucursal = getObjeto().getSucursal().getIdSucursal();
		}
		getObjeto().setBodegaExistencias(bodegaExistenciaService.findByIngreso(getObjeto()));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {

		if (!isEdit()) {
			for (BodegaExistencia bExistencia : existencias) {
				bExistencia.setBodegaIngreso(getObjeto());
				bodegaExistenciaService.save(bExistencia);
			}
			generarNumeroFactura();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setFechaIngreso(new Date());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		if (selSucursal == 0) {
			mensajeError("Por favor selecciona una sucursal válida");
			return false;
		}

		Sucursal suc = sucursalService.findById(selSucursal);

		if (suc != null) {
			getObjeto().setSucursal(suc);
		}

		if (!isEdit()) {
			existencias = getObjeto().getBodegaExistencias();
			for (BodegaExistencia ext : getObjeto().getBodegaExistencias()) {
//				ext.setBodegaIngreso(getObjeto());
				ext.setEstado(EstadoExistenciaEnum.DISPONIBLE);
				ext.setPrecioCompra(getObjeto().getPrecioCompra().doubleValue());
				ext.setPrecioVenta(getObjeto().getPrecioVenta());
				ext.setPrecioVentaMayoristas(getObjeto()
						.getPrecioVentaMayoristas());
				ext.setColor(getObjeto().getColor());
				ext.setSucursal(suc);
				if (bodegaExistenciaService.findByBarcode(ext.getBarCode()) != null) {
					mensajeError("Este PID " + ext.getBarCode()
							+ " ya se encuentra en el inventario");
					return false;
				}

			}
		}

		return true;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param evt
	 */
	public void cambioItem() {
		item = bodegaItemService.findById(selItem);
		getObjeto().setPrecioVenta(item.getPrecioVenta());
		getObjeto().setPrecioVentaMayoristas(item.getPrecioVentaMayoristas());
		
		existemp = new BodegaExistencia();

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 */
	public void addExistencia() {

		if (item == null) {
			mensajeError("Selecciona un item");
			return;
		}
		if (productoId == null || productoId.equals("")) {
			mensajeError("Digita un PID válido");
			return;
		}

		if (item.getCantidadImei() == 1) {

			existemp.setBarCode(productoId);
			existemp.setBodegaItemBean(item);
			existemp.setSucursal(login.getSucursal());
			if (getObjeto().getBodegaExistencias().contains(existemp)) {
				mensajeError("Este PID ya está en la lista");
				return;
			}
			getObjeto().addBodegaExistencia(existemp);
			existemp = new BodegaExistencia();
			productoId = "";
			item.setContadorImei(0);
			return;
		}

		if (item.getContadorImei() == 1) {
			existemp.setBarCode(productoId);

			if (item.getCantidadImei() == 1) {
				existemp.setBarCode(productoId);
				existemp.setBodegaItemBean(item);
				existemp.setSucursal(login.getSucursal());
				if (getObjeto().getBodegaExistencias().contains(existemp)) {
					mensajeError("Este PID ya está en la lista");
					return;
				}
				getObjeto().addBodegaExistencia(existemp);
				existemp = new BodegaExistencia();
				productoId = "";
				return;
			}
			item.setContadorImei(item.getContadorImei() + 1);
			productoId = "";
			return;
		} else {

			if (item.getContadorImei() == 2) {
				existemp.setBarCode2(productoId);

				if (item.getCantidadImei() == 2) {

					existemp.setBarCode2(productoId);
					existemp.setBodegaItemBean(item);
					existemp.setSucursal(login.getSucursal());
					if (getObjeto().getBodegaExistencias().contains(existemp)) {
						mensajeError("Este PID ya está en la lista");
						return;
					}
					getObjeto().addBodegaExistencia(existemp);
					existemp = new BodegaExistencia();
					productoId = "";
					return;
				}
				item.setContadorImei(item.getContadorImei() + 1);
				productoId = "";
				return;
			}

			if (item.getContadorImei() == 3) {
				existemp.setBarCode2(productoId);

				if (item.getCantidadImei() == 3) {

					existemp.setBarCode3(productoId);
					existemp.setBodegaItemBean(item);
					existemp.setSucursal(login.getSucursal());
					if (getObjeto().getBodegaExistencias().contains(existemp)) {
						mensajeError("Este PID ya está en la lista");
						return;
					}
					getObjeto().addBodegaExistencia(existemp);
					existemp = new BodegaExistencia();
					productoId = "";
					return;
				}
				item.setContadorImei(item.getContadorImei() + 1);
				productoId = "";
				return;
			}
			productoId = "";
		}

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

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the selItem
	 */
	public int getSelItem() {
		return selItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param selItem
	 *            the selItem to set
	 */
	public void setSelItem(int selItem) {
		this.selItem = selItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param productoId
	 *            the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @return the selSucursal
	 */
	public int getSelSucursal() {
		return selSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param selSucursal
	 *            the selSucursal to set
	 */
	public void setSelSucursal(int selSucursal) {
		this.selSucursal = selSucursal;
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
	 * @date 14/01/2014
	 * @return the terceroService
	 */
	public TerceroService getTerceroService() {
		return terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the bodegaIngresoService
	 */
	public BodegaIngresoService getBodegaIngresoService() {
		return bodegaIngresoService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param bodegaIngresoService
	 *            the bodegaIngresoService to set
	 */
	public void setBodegaIngresoService(
			BodegaIngresoService bodegaIngresoService) {
		this.bodegaIngresoService = bodegaIngresoService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @return the bodegaItemService
	 */
	public BodegaItemService getBodegaItemService() {
		return bodegaItemService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param bodegaItemService
	 *            the bodegaItemService to set
	 */
	public void setBodegaItemService(BodegaItemService bodegaItemService) {
		this.bodegaItemService = bodegaItemService;
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

}
