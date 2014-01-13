/**
 * 
 */
package co.innovate.rentavoz.views.session;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import co.innovate.rentavoz.commons.BaseBean;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.caja.CajaService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.services.usuario.UsuarioService;

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

	private Usuario user;
	private String usuario;
	private String contrasena;
	private Tercero tercero;
	private Sucursal sucursal;

	private double valorCaja;

	private Logger log = Logger.getAnonymousLogger();

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 */
	@PostConstruct
	public void init() {

		user = null;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 12/11/2013
	 */
	public void updateValorCaja() {
		try {

			valorCaja = cajaService.valorCaja();
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
			// cajaEjb.abrirCaja(user);
			// valorCaja=cajaEjb.valorCaja();
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
		if (tercero != null) {
			if (!tercero.getSucursalTerceroList().isEmpty()) {
				sucursal = tercero.getSucursalTerceroList().get(0)
						.getSucursalidSucursal();

			}
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
		// String ctxPath = ((ServletContext)
		// ctx.getContext()).getContextPath();

		try {
			// Usar el contexto de JSF para invalidar la sesión,
			// NO EL DE SERVLETS (nada de HttpServletRequest)
			((HttpSession) ctx.getSession(false)).invalidate();

			// Redirección de nuevo con el contexto de JSF,
			// si se usa una HttpServletResponse fallará.
			// Sin embargo, como ya está fuera del ciclo de vida
			// de JSF se debe usar la ruta completa -_-U
			// ctx.redirect(ctxPath + "/");
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

}
