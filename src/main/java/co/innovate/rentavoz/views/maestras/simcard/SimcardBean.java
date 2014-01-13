package co.innovate.rentavoz.views.maestras.simcard;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.SucursalSimcard;
import co.innovate.rentavoz.model.almacen.EstadosSimcardEnum;
import co.innovate.rentavoz.model.almacen.Simcard;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.simcard.SimcardService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.sucursalsimcard.SucursalSimcardService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class SimcardBean
 * @date 17/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class SimcardBean extends StandardAbm<Simcard,Integer> {

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/maestras/simcard/index.jsf";

	private static final long serialVersionUID = -1689462037286702729L;

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         estadoSim
	 */
	private String estadoSim;

	private String sucursal;

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         simcardFacade
	 */
	@ManagedProperty(value="#{simcardService}")
	private SimcardService simcardService;

	@ManagedProperty(value="#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty(value="#{sucursalSimcardService}")
	private SucursalSimcardService sucursalSimcardService;

	@ManagedProperty(value = "#{login}")
	private Login login;


	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setFecha(new Date());
		getObjeto().setSimEstado(EstadosSimcardEnum.EN_BLANCO);
		estadoSim = getObjeto().getEstadoAsString();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public Simcard getInstancia() {

		return new Simcard();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {

		return REGLA_NAVEGACION;
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public Simcard getObjeto() {

		return obtenerObjeto();
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<Simcard> getListado() {
		return obtenerListado();
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/07/2013
	 * @return
	 */
	public String guardarSim() {
		try {
			if (!isEdit()) {

				if (simcardService.findByScId(getObjeto().getSimIccid()) == null) {

					aceptar();
					guardarHistoriaSucursal();
					setObjeto(getInstancia());
					getObjeto().setFecha(new Date());
					return "";
				} else {
					mensajeError("Ya existe una Sim card con este SCID");
					return "";

				}
			} else {
				aceptar();

				setObjeto(getInstancia());
				getObjeto().setFecha(new Date());
				return reglaNavegacion();
			}

		} catch (Exception e) {
			mensajeError("Error al guardar los datos " + e.toString());
			return "";
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 */
	public void guardarHistoriaSucursal() {

		if (!isEdit()) {
			SucursalSimcard ss = new SucursalSimcard();
			ss.setSimcardidSimcard(getObjeto());
			ss.setSucursalidSucursal(getObjeto().getSucursal());
			ss.setSucSimEstado(0);
			ss.setSucSimObservacion(" ");
			ss.setFecha(new Date());
			sucursalSimcardService.save(ss);

		}
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {

		estadoSim = getObjeto().getEstadoAsString();
		if (getObjeto().getSucursal() != null) {
			sucursal = String
					.valueOf(getObjeto().getSucursal().getIdSucursal());
		}
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		estadoSim = "";

	}

	/**
	 * * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		try {

			getObjeto().setSimEstado(
					EstadosSimcardEnum.valueOf(EstadosSimcardEnum.class,
							estadoSim));
			getObjeto().setSucursal(
					sucursalService.findById(Integer.valueOf(sucursal)));
			if (!isEdit()) {

				if (simcardService.findByScId(getObjeto().getSimIccid()) == null) {
					return true;
				} else {
					mensajeError("Este ICCID ya está siendo utilizado , por favor intente con otro");
					return false;
				}
			} else {

				return true;
			}
		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @return the estadoSim
	 */
	public String getEstadoSim() {
		return estadoSim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param estadoSim
	 *            the estadoSim to set
	 */
	public void setEstadoSim(String estadoSim) {
		this.estadoSim = estadoSim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Simcard, Integer> getFacade() {
		return simcardService;
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
	 * @param simcardService the simcardService to set
	 */
	public void setSimcardService(SimcardService simcardService) {
		this.simcardService = simcardService;
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
	 * @param sucursalService the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the sucursalSimcardService
	 */
	public SucursalSimcardService getSucursalSimcardService() {
		return sucursalSimcardService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param sucursalSimcardService the sucursalSimcardService to set
	 */
	public void setSucursalSimcardService(
			SucursalSimcardService sucursalSimcardService) {
		this.sucursalSimcardService = sucursalSimcardService;
	}
	
	
}
