/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.linea;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.criterion.Order;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.empresa.EmpresaService;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.plan.PlanService;
import co.innovate.rentavoz.services.planlinea.PlanLineaService;
import co.innovate.rentavoz.services.simcard.SimcardService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteColaboradores;
import co.innovate.rentavoz.views.components.buscador.BuscadorPlan;
import co.innovate.rentavoz.views.components.buscador.BuscadorSimCard;
import co.innovate.rentavoz.views.session.Login;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class LineaBean
 * @date 17/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class LineaBean extends StandardAbm<Linea, Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         facade
	 */
	@ManagedProperty(value = "#{empresaService}")
	private EmpresaService empresaService;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         terceroFacade
	 */
	@ManagedProperty(value = "#{estadoLineaService}")
	private EstadoLineaService estadoLineaService;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         lineaFacade
	 */
	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         planFacade
	 */
	@ManagedProperty(value = "#{planService}")
	private PlanService planService;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         plFacade
	 */
	@ManagedProperty(value = "#{planLineaService}")
	private PlanLineaService planLineaService;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         simcardFacade
	 */
	@ManagedProperty(value = "#{simcardService}")
	private SimcardService simcardService;

	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         empresa
	 */
	private String empresa = null;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         estadoLinea
	 */
	private String estadoLinea = null;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         buscadorPlan
	 */
	private BuscadorPlan buscadorPlan;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         buscadorSimCard
	 */
	private BuscadorSimCard buscadorSimCard;

	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         planOLd
	 */
	private Plan planOLd;

	private String idPlan;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	
	private AutocompleteColaboradores autocompleteColaboradores;
	
	private String idSucursal;
	
	 @ManagedProperty(value="#{login}")
	 private Login login;
	private List<Sucursal> sucursales;
	private Boolean verTodas;

	@Override
	public GenericService<Linea, Integer> getFacade() {
		return lineaService;
	}

	@Override
	public Linea getInstancia() {
		return new Linea();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/linea/index.jsf";
	}

	@Override
	public Linea getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Linea> getListado() {
		return obtenerListado();
	}

	@Override
	public void postFormNuevo() {
		getObjeto().setIdLinea(lineaService.nextCodigo());
		getObjeto().setFecha(new Date());
	}

	@Override
	public void initialize() {
		buscadorPlan = new BuscadorPlan() {

			@Override
			public void selCentrope(Plan centrope) {

				getObjeto().setPlan(centrope);

			}

			@Override
			public PlanService getService() {
				return planService;
			}
		};

		buscadorSimCard = new BuscadorSimCard() {

			@Override
			public void selCentrope(Simcard centrope) {
				getObjeto().setSimcard(centrope);
			}

			@Override
			public SimcardService getService() {
				return simcardService;
			}

		};
		autocompleteColaboradores=new AutocompleteColaboradores() {
			
			@Override
			public void postSelect() {
				getObjeto().setEncargado(seleccionado);
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
sucursales=login.getSucursales();

	}
	
	public void cambioVerTodas(ValueChangeEvent event){
		verTodas= (Boolean)event.getNewValue();
		if (verTodas) {
			sucursales=sucursalService.findAll();
		}else{
			sucursales=login.getSucursales();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String)
	 */
	@Override
	public List<Linea> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		Order order = null;
		if (sortField != null) {

			switch (sortOrder) {
			case ASCENDING:
				order = Order.asc(sortField);
				break;

			case DESCENDING:
				order = Order.desc(sortField);
				break;
			case UNSORTED:

				break;
			}
		}

		return lineaService.findByCriteria(globalFilter, startingAt,
				maxPerPage, order,sucursales);
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
		getModel().setNumeroRegistros(lineaService.countByCriteria(globalFilter,sucursales));
		return lineaService.countByCriteria(globalFilter,sucursales);
	}

	@Override
	public void buscarrPorCriterio() {

	}

	@Override
	public void preRenderizarItem() {
		estadoLinea = getObjeto().getEstadoLineaidEstadoLinea()
				.getIdEstadoLinea() + "";
		empresa = getObjeto().getEmpresaidEmpresa().getIdEmpresa() + "";
		planOLd = getObjeto().getPlan();
		idSucursal = getObjeto().getSucursal() != null ? getObjeto()
				.getSucursal().getIdSucursal() + "" : "";

		idPlan = getObjeto().getPlan() == null ? null : getObjeto().getPlan()
				.getIdPlan().toString();
		if (getObjeto().getEncargado()!=null) {
			autocompleteColaboradores.setSeleccionado(getObjeto().getEncargado());
			autocompleteColaboradores.setQuery(getObjeto().getEncargado().toString());
		}

	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		if (idSucursal == null) {
			mensajeError("Sucursal es nulo");
			return false;
		}
		Sucursal sucursal = null;
		try {
			sucursal = sucursalService.findById(Integer.parseInt(idSucursal));
			getObjeto().setSucursal(sucursal);
		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}

		if (idPlan == null) {
			mensajeError("Por favor selecciona un plan");
			return false;
		}

		if (getObjeto().getLinCorte() == 0) {
			mensajeError("El corte de la linea no debe ser cero(0)");
			return false;
		}

		getObjeto().setSucursal(sucursal);

		Plan plan = null;
		try {
			plan = planService.findById(Integer.valueOf(idPlan));
			getObjeto().setPlan(plan);
		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}

		if (isEdit()) {

			if (lineaService.findBNumero2(getObjeto().getLinNumero())) {
				getObjeto().setEmpresaidEmpresa(
						empresaService.findById(Integer.valueOf(empresa)));
				getObjeto().setEstadoLineaidEstadoLinea(
						estadoLineaService.findById(Integer
								.parseInt(estadoLinea)));
				return true;
			} else {
				mensaje("Error",
						"El numero de linea ya esta en uso por favor intente con otro numero");
				return false;
			}

		} else {

			if (empresa == null || estadoLinea == null

			) {
				if (empresa == null) {
					mensajeError("Selecciona una empresa");
				}
				if (estadoLinea == null) {
					mensajeError("Selecciona un estado");
				}
				return false;
			}

			if (empresa == null || estadoLinea == null) {
				mensajeError("Para poder continuar por favor diligencia "
						+ empresa == null ? "empresa"
						: "" + " " + estadoLinea == null ? "estado linea " : "");
				return false;
			} else {
				if (lineaService.findById(getObjeto().getIdLinea()) == null
						&& lineaService.findBNumero(getObjeto().getLinNumero())) {
					getObjeto().setEmpresaidEmpresa(
							empresaService.findById(Integer.valueOf(empresa)));
					getObjeto().setEstadoLineaidEstadoLinea(
							estadoLineaService.findById(Integer
									.parseInt(estadoLinea)));
					return true;
				} else {
					mensajeError("El codigo o numero  de linea ya esta siendo utilizado");
					return false;
				}
			}

		}

	}

	/**
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {
		try {

			planLineaService.activarPorLineaYPlan(getObjeto(), getObjeto()
					.getPlan());
		} catch (Exception e) {
			return;
		}
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstadoLinea() {
		return estadoLinea;
	}

	public void setEstadoLinea(String estadoLinea) {
		this.estadoLinea = estadoLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @return the buscadorPlan
	 */
	public BuscadorPlan getBuscadorPlan() {
		return buscadorPlan;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param buscadorPlan
	 *            the buscadorPlan to set
	 */
	public void setBuscadorPlan(BuscadorPlan buscadorPlan) {
		this.buscadorPlan = buscadorPlan;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @return the buscadorSimCard
	 */
	public BuscadorSimCard getBuscadorSimCard() {
		return buscadorSimCard;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @param buscadorSimCard
	 *            the buscadorSimCard to set
	 */
	public void setBuscadorSimCard(BuscadorSimCard buscadorSimCard) {
		this.buscadorSimCard = buscadorSimCard;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the idSucursal
	 */
	public String getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param idSucursal
	 *            the idSucursal to set
	 */
	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @return the idPlan
	 */
	public String getIdPlan() {
		return idPlan;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param idPlan
	 *            the idPlan to set
	 */
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the estadoLineaService
	 */
	public EstadoLineaService getEstadoLineaService() {
		return estadoLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param estadoLineaService
	 *            the estadoLineaService to set
	 */
	public void setEstadoLineaService(EstadoLineaService estadoLineaService) {
		this.estadoLineaService = estadoLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the lineaService
	 */
	public LineaService getLineaService() {
		return lineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param lineaService
	 *            the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the planService
	 */
	public PlanService getPlanService() {
		return planService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param planService
	 *            the planService to set
	 */
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the planLineaService
	 */
	public PlanLineaService getPlanLineaService() {
		return planLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param planLineaService
	 *            the planLineaService to set
	 */
	public void setPlanLineaService(PlanLineaService planLineaService) {
		this.planLineaService = planLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the simcardService
	 */
	public SimcardService getSimcardService() {
		return simcardService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param simcardService
	 *            the simcardService to set
	 */
	public void setSimcardService(SimcardService simcardService) {
		this.simcardService = simcardService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the planOLd
	 */
	public Plan getPlanOLd() {
		return planOLd;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param planOLd
	 *            the planOLd to set
	 */
	public void setPlanOLd(Plan planOLd) {
		this.planOLd = planOLd;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the sucursalService
	 */
	public SucursalService getSucursalService() {
		return sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param empresaService
	 *            the empresaService to set
	 */
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the autocompleteColaboradores
	 */
	public AutocompleteColaboradores getAutocompleteColaboradores() {
		return autocompleteColaboradores;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param autocompleteColaboradores the autocompleteColaboradores to set
	 */
	public void setAutocompleteColaboradores(
			AutocompleteColaboradores autocompleteColaboradores) {
		this.autocompleteColaboradores = autocompleteColaboradores;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the verTodas
	 */
	public Boolean getVerTodas() {
		return verTodas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param verTodas the verTodas to set
	 */
	public void setVerTodas(Boolean verTodas) {
		this.verTodas = verTodas;
	}
}
