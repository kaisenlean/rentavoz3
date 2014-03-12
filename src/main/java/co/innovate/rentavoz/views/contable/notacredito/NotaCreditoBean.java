/**
 * 
 */
package co.innovate.rentavoz.views.contable.notacredito;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.criterion.Order;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.facturacion.NotaCredito;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.facturacion.NotaCreditoService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoBean
 * @date 11/03/2014
 * 
 */
@ManagedBean
@ViewScoped
public class NotaCreditoBean extends StandardAbm<NotaCredito, Integer>
		implements Serializable {

	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ID
	 */
	private static final String ID = "id";

	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         PAGINAS_CONTABLE_NOTACREDITO_INDEX_JSF
	 */
	private static final String PAGINAS_CONTABLE_NOTACREDITO_INDEX_JSF = "/paginas/contable/notacredito/index.jsf";

	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{notaCreditoService}")
	private NotaCreditoService notaCreditoService;

	@ManagedProperty(value = "#{login}")
	private Login login;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	private AutocompleteTercero autocompleteCliente;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	private int selSucursal;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param notaCreditoService
	 *            the notaCreditoService to set
	 */
	public void setNotaCreditoService(NotaCreditoService notaCreditoService) {
		this.notaCreditoService = notaCreditoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		selSucursal = getObjeto().getSucursal().getIdSucursal();
		autocompleteCliente.setSeleccionado(getObjeto().getCliente());
		autocompleteCliente.setQuery(getObjeto().getCliente()
				.getNombreCompleto());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {

		getObjeto().setCreador(login.getTercero());
		getObjeto().setFechaEmision(Calendar.getInstance().getTime());
		getObjeto().setValor(BigInteger.ZERO.doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		if (getObjeto().getValor() <= 0) {
			mensajeError("Digita un valor mayor a cero (0) para la nota de crédito");
			return false;
		}
		
		if (selSucursal==0) {
			mensajeError("Selecciona una sucursal válida");
			return false;
		}
		
		getObjeto().setSucursal(sucursalService.findById(selSucursal));
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<NotaCredito, Integer> getFacade() {
		return notaCreditoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public NotaCredito getInstancia() {
		return new NotaCredito();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return PAGINAS_CONTABLE_NOTACREDITO_INDEX_JSF;
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
		return notaCreditoService.countByCriteria(globalFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<NotaCredito> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		Order order;
		switch (sortOrder) {
		case ASCENDING:
			order = Order.asc(sortField == null ? ID : sortField);
			break;

		case DESCENDING:
			order = Order.desc(sortField == null ? ID : sortField);
			break;

		case UNSORTED:
			order = Order.asc(ID);
			break;
		default:
			order = Order.asc(ID);
			break;
		}
		return notaCreditoService.findByCriteria(globalFilter, startingAt,
				maxPerPage, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public NotaCredito getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<NotaCredito> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		autocompleteCliente = new AutocompleteTercero() {

			@Override
			public void postSelect() {
				getObjeto().setCliente(seleccionado);
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @return the autocompleteCliente
	 */
	public AutocompleteTercero getAutocompleteCliente() {
		return autocompleteCliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param autocompleteCliente
	 *            the autocompleteCliente to set
	 */
	public void setAutocompleteCliente(AutocompleteTercero autocompleteCliente) {
		this.autocompleteCliente = autocompleteCliente;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @return the selSucursal
	 */
	public int getSelSucursal() {
		return selSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param selSucursal
	 *            the selSucursal to set
	 */
	public void setSelSucursal(int selSucursal) {
		this.selSucursal = selSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
}
