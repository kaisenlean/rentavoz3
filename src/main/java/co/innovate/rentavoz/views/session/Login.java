/**
 * 
 */
package co.innovate.rentavoz.views.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.caja.CajaService;
import co.innovate.rentavoz.services.sucursaltercero.SucursalTerceroService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.usuario.UsuarioService;
import co.innovate.rentavoz.views.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class Login
 * @date 15/07/2013
 * 
 */

@ManagedBean(eager = true)
@SessionScoped
public class Login extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;

	@ManagedProperty(value = "#{terceroService}")
	private TerceroService terceroService;

	@ManagedProperty(value = "#{cajaService}")
	private CajaService cajaService;

	private List<Sucursal> sucursales = new ArrayList<Sucursal>();

	@ManagedProperty(value = "#{sucursalTerceroService}")
	private SucursalTerceroService sucursalTerceroService;

	private Usuario user;
	private String usuario;
	private String contrasena;
	private Tercero tercero;
	private Sucursal sucursal;

	private double valorCaja;
	private double valorCajaLineas;

	private Boolean pertenecePrinicpal;

	private Logger log = Logger.getAnonymousLogger();
	
	private List<SelectItem> sucursalItems= new ArrayList<SelectItem>();

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 */
	@PostConstruct
	public void init() {

		user = null;
		logOut();
	}

	public void validateSession() {

		if (user == null) {
			logOut();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 */
	public void updateValorCaja() {
		try {

			valorCaja = cajaService.valorCaja(getTercero());
			valorCajaLineas = cajaService.valorCajaLineas(getTercero());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString());
		}

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 * @return
	 */
	public String login() {
		RequestContext context = RequestContext.getCurrentInstance();

		boolean loggedIn = false;
		try {

			user = usuarioService.login(usuario, contrasena);
			if (user == null) {
				loggedIn = false;
				context.addCallbackParam("loggedIn", loggedIn);
				mensajeError("Credenciales invalidas");
				return null;
			}
			loggedIn = true;
			context.addCallbackParam("loggedIn", loggedIn);
			buscarTercero();
			cajaService.abrirCaja(user);
			valorCaja = cajaService.valorCaja(getTercero());
			valorCajaLineas = cajaService.valorCajaLineas(getTercero());
			return "/dashboard.jsf";
		} catch (Exception e) {
			loggedIn = false;
			context.addCallbackParam("loggedIn", loggedIn);
			mensajeError("Credenciales invalidas");
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 */
	private void buscarTercero() {
		tercero = terceroService.findByUsuario(user);
		sucursales = new ArrayList<Sucursal>();
		if (tercero == null) {
			mensajeError("Por favor asignale un tercero a este usuario para poder ingresar");
			return;
		}
		if (user.getAdministrador()) {
			tercero.setSucursalTerceroList(sucursalTerceroService.findAll());
		} else {
			tercero.setSucursalTerceroList(sucursalTerceroService
					.findByTercero(tercero));
		}

		if (!tercero.getSucursalTerceroList().isEmpty()) {
			sucursal = tercero.getSucursalTerceroList().get(0)
					.getSucursalidSucursal();
			for (SucursalTercero sucursalTercero : tercero
					.getSucursalTerceroList()) {
				sucursales.add(sucursalTercero.getSucursalidSucursal());

			}
			if (user.getAdministrador()) {
				pertenecePrinicpal=true;
			}else{

			for (Sucursal sucursal : sucursales) {
				if (sucursal.getPrincipal()) {
					pertenecePrinicpal = true;
					break;
				}
			}
			}
		}
		
		cargarItemsBySucursales(sucursales);

	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	* @param sucursales2
	*/
	private void cargarItemsBySucursales(List<Sucursal> sucursales2) {
		sucursalItems=new ArrayList<SelectItem>();
		for (Sucursal sucursal : sucursales2) {
			sucursalItems.add(new SelectItem(sucursal.getIdSucursal(),sucursal.getSucNombre()));
			
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 */
	public void logOut() {
		ExternalContext ctx = FacesContext.getCurrentInstance()
				.getExternalContext();
		String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

		try {
			// Usar el contexto de JSF para invalidar la sesión,
			// NO EL DE SERVLETS (nada de HttpServletRequest)
			((HttpSession) ctx.getSession(false)).invalidate();

			// Redirección de nuevo con el contexto de JSF,
			// si se usa una HttpServletResponse fallará.
			// Sin embargo, como ya está fuera del ciclo de vida
			// de JSF se debe usar la ruta completa -_-U
			ctx.redirect(ctxPath + "/");
			removeCookie(LAST_URL);
			addCookie(LAST_URL,"/" );
		} catch (Exception ex) {
			mensaje("Error", ex.toString());
		}
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param user
	 *            the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param contrasena
	 *            the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return
	 */
	public boolean isSession() {

		return user != null ? true : false;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/11/2013
	 * @return the valorCaja
	 */
	public double getValorCaja() {
		return valorCaja;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/11/2013
	 * @param valorCaja
	 *            the valorCaja to set
	 */
	public void setValorCaja(double valorCaja) {
		this.valorCaja = valorCaja;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return the usuarioService
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param usuarioService
	 *            the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return the terceroService
	 */
	public TerceroService getTerceroService() {
		return terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param terceroService
	 *            the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return the cajaService
	 */
	public CajaService getCajaService() {
		return cajaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param cajaService
	 *            the cajaService to set
	 */
	public void setCajaService(CajaService cajaService) {
		this.cajaService = cajaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/01/2014
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the sucursales
	 */
	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param sucursales
	 *            the sucursales to set
	 */
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the pertenecePrinicpal
	 */
	public Boolean getPertenecePrinicpal() {
		return pertenecePrinicpal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param pertenecePrinicpal
	 *            the pertenecePrinicpal to set
	 */
	public void setPertenecePrinicpal(Boolean pertenecePrinicpal) {
		this.pertenecePrinicpal = pertenecePrinicpal;
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
	 * @date 9/02/2014
	 * @return the valorCajaLineas
	 */
	public double getValorCajaLineas() {
		return valorCajaLineas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/02/2014
	 * @param valorCajaLineas
	 *            the valorCajaLineas to set
	 */
	public void setValorCajaLineas(double valorCajaLineas) {
		this.valorCajaLineas = valorCajaLineas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the sucursalItems
	 */
	public List<SelectItem> getSucursalItems() {
		return sucursalItems;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param sucursalItems the sucursalItems to set
	 */
	public void setSucursalItems(List<SelectItem> sucursalItems) {
		this.sucursalItems = sucursalItems;
	}
}
