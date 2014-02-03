/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.tercero;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TipoTerceroEnum;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.usuario.UsuarioService;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteCiudad;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteUsuario;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanTercero
 * @date 24/07/2013pr
 * 
 */
@ManagedBean
@ViewScoped
public class BeanTercero extends StandardAbm<Tercero, Integer> implements
		Serializable {

	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         MSG_SELECCIONA_UN_TERCERO
	 */
	private static final String MSG_SELECCIONA_UN_TERCERO = "- - Selecciona un tipo de tercero - -";
	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         SPACE
	 */
	private static final String SPACE = " ";
	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         UNDERLINE_VALUE
	 */
	private static final String UNDERLINE_VALUE = "_";
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	@ManagedProperty(value = "#{centropeService}")
	private CentropeService centropeService;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	private String tipo;

	private List<SelectItem> itemTipo = new ArrayList<SelectItem>();
	private boolean callByOtherModule;

	private int selCentrope;

	private AutocompleteCiudad autocompleteCiudad;

	@ManagedProperty(value = "#{ciudadService}")
	private CiudadService ciudadService;

	@ManagedProperty(value = "#{sucursalTerceroService}")
	private SucursalTerceroService sucursalTerceroService;

	
	
	private  List<SucursalTercero> eliminados=new ArrayList<SucursalTercero>();
	private  List<SucursalTercero> sucursales=new ArrayList<SucursalTercero>();
	
	private AutocompleteUsuario autocompleteUsuario;
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	
	/**
	 * 2/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         sucursalTercero
	 */
	private SucursalTercero sucursalTercero = new SucursalTercero();

	private Integer selSucursal;

	public void addSucursal() {
		if (selSucursal == null) {
			mensajeError("Por favor selecciona una sucursal");
			return;
		}
		if (selSucursal.equals(BigInteger.ZERO)) {
			mensajeError("Por favor selecciona una sucursal");
			return;
		}
		sucursalTercero.setSucursalidSucursal(sucursalService
				.findById(selSucursal));
		sucursalTercero.setTerceroidTecero(getObjeto());
		sucursalTercero.setSucTerEstado(BigInteger.ONE.intValue());

		if (getObjeto().getSucursalTerceroList().contains(sucursalTercero)) {
			mensajeError("Este tercero ya tiene asignada esta sucursal");
			return;
		}
		getObjeto().getSucursalTerceroList().add(sucursalTercero);

		sucursalTercero = new SucursalTercero();
		selSucursal = BigInteger.ZERO.intValue();
	}

	public void removeSucursal(SucursalTercero sucursalTercero) {
		getObjeto().getSucursalTerceroList().remove(sucursalTercero);
		if (sucursalTercero.getIdSucursalTercero() == null) {
			return;
		}

		getObjeto().getEliminados().add(sucursalTercero);
	}

	@Override
	public GenericService<Tercero, Integer> getFacade() {
		return terceroService;
	}

	@Override
	public Tercero getInstancia() {
		return new Tercero();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/tercero/index.jsf";
	}

	@Override
	public Tercero getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Tercero> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		setObjeto(new Tercero());
		itemTipo.clear();
		TipoTerceroEnum[] enums = TipoTerceroEnum.values();
		itemTipo.add(new SelectItem("", MSG_SELECCIONA_UN_TERCERO));
		for (int i = 0; i < enums.length; i++) {
			itemTipo.add(new SelectItem(enums[i].name(), enums[i].name()
					.replace(UNDERLINE_VALUE, SPACE)));
		}
		if (getAttribute(SessionParams.CREATE_TERCERO_ON_LOAD) != null) {
			callByOtherModule = true;
			form = true;

		}

		autocompleteCiudad = new AutocompleteCiudad() {

			@Override
			public void postSelect() {
				getObjeto().setCiudad(seleccionado);
			}

			@Override
			public CiudadService getFacade() {
				return ciudadService;
			}
		};
		autocompleteUsuario=new AutocompleteUsuario() {
			
			@Override
			public void postSelect() {

				getObjeto().setUsuario(seleccionado);
			}
			
			@Override
			public UsuarioService getFacade() {
				return usuarioService;
			}
		};
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {

		for (SucursalTercero sucursalTercero : eliminados) {
			sucursalTerceroService.delete(sucursalTercero);
		}


		for (SucursalTercero sucursalTercero : sucursales) {
			sucursalTercero.setTerceroidTecero(getObjeto());
			sucursalTerceroService.save(sucursalTercero);
		}
		
		
		if (callByOtherModule) {
			String reglaNav = (String) getAttribute(SessionParams.MODULE_URI);
			removeAttribute(SessionParams.MODULE_URI);
			removeAttribute(SessionParams.CREATE_TERCERO_ON_LOAD);
			addAttribute(SessionParams.ENTITY_BACK, getObjeto());
			reglaNavegacionAlterna = reglaNav;
		}
	}

	@Override
	public boolean preAction() {
		eliminados=getObjeto().getEliminados();
		sucursales=getObjeto().getSucursalTerceroList();
		if (selCentrope == 0) {
			mensajeError("Selecciona un centro de operaciones");
			return false;
		}
		getObjeto().setCentrope(centropeService.findById(selCentrope));

		if (tipo != null) {
			getObjeto().setTipo(
					TipoTerceroEnum.valueOf(TipoTerceroEnum.class, tipo));
			return true;
		} else {
			return true;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		if (getObjeto().getTipo() != null) {
			tipo = getObjeto().getTipo().name();
		}
		if (getObjeto().getCentrope() != null) {
			selCentrope = getObjeto().getCentrope().getId();
		}
		if (getObjeto().getCiudad() != null) {
			autocompleteCiudad.setQuery(getObjeto().getCiudad().toString());
			autocompleteCiudad.setSeleccionado(getObjeto().getCiudad());
		}
		
		if (getObjeto().getUsuario()!=null) {
			autocompleteUsuario.setQuery(getObjeto().getUsuario().toString());
			autocompleteUsuario.setSeleccionado(getObjeto().getUsuario());
		}
		getObjeto().setSucursalTerceroList(sucursalTerceroService.findByTercero(getObjeto()));

	}

	@Override
	public void buscarrPorCriterio() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the itemTipo
	 */
	public List<SelectItem> getItemTipo() {
		return itemTipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param itemTipo
	 *            the itemTipo to set
	 */
	public void setItemTipo(List<SelectItem> itemTipo) {
		this.itemTipo = itemTipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @return the callByOtherModule
	 */
	public boolean isCallByOtherModule() {
		return callByOtherModule;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param callByOtherModule
	 *            the callByOtherModule to set
	 */
	public void setCallByOtherModule(boolean callByOtherModule) {
		this.callByOtherModule = callByOtherModule;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
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
	public List<Tercero> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the selCentrope
	 */
	public int getSelCentrope() {
		return selCentrope;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param selCentrope
	 *            the selCentrope to set
	 */
	public void setSelCentrope(int selCentrope) {
		this.selCentrope = selCentrope;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param centropeService
	 *            the centropeService to set
	 */
	public void setCentropeService(CentropeService centropeService) {
		this.centropeService = centropeService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param ciudadService
	 *            the ciudadService to set
	 */
	public void setCiudadService(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param autocompleteCiudad
	 *            the autocompleteCiudad to set
	 */
	public void setAutocompleteCiudad(AutocompleteCiudad autocompleteCiudad) {
		this.autocompleteCiudad = autocompleteCiudad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @return the autocompleteCiudad
	 */
	public AutocompleteCiudad getAutocompleteCiudad() {
		return autocompleteCiudad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the sucursalTercero
	 */
	public SucursalTercero getSucursalTercero() {
		return sucursalTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param sucursalTercero
	 *            the sucursalTercero to set
	 */
	public void setSucursalTercero(SucursalTercero sucursalTercero) {
		this.sucursalTercero = sucursalTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the selSucursal
	 */
	public Integer getSelSucursal() {
		return selSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param selSucursal
	 *            the selSucursal to set
	 */
	public void setSelSucursal(Integer selSucursal) {
		this.selSucursal = selSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param sucursalTerceroService
	 *            the sucursalTerceroService to set
	 */
	public void setSucursalTerceroService(
			SucursalTerceroService sucursalTerceroService) {
		this.sucursalTerceroService = sucursalTerceroService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the sucursales
	 */
	public List<SucursalTercero> getSucursales() {
		return sucursales;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(List<SucursalTercero> sucursales) {
		this.sucursales = sucursales;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the eliminados
	 */
	public List<SucursalTercero> getEliminados() {
		return eliminados;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param eliminados the eliminados to set
	 */
	public void setEliminados(List<SucursalTercero> eliminados) {
		this.eliminados = eliminados;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/02/2014
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/02/2014
	 * @return the usuarioService
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/02/2014
	 * @return the autocompleteUsuario
	 */
	public AutocompleteUsuario getAutocompleteUsuario() {
		return autocompleteUsuario;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/02/2014
	 * @param autocompleteUsuario the autocompleteUsuario to set
	 */
	public void setAutocompleteUsuario(AutocompleteUsuario autocompleteUsuario) {
		this.autocompleteUsuario = autocompleteUsuario;
	}
}
