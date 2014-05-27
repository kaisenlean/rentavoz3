/**
 * 
 */
package co.innovate.rentavoz.views.session;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.services.usuario.UsuarioService;
import co.innovate.rentavoz.views.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ChangePass
 * @date 27/05/2014
 *
 */
@ManagedBean
@ViewScoped
public class ChangePass extends BaseBean implements Serializable {

	/**
	 * 27/05/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	
	private String clave=StringUtils.EMPTY;
	private String clave2=StringUtils.EMPTY;
	
	
	
public void cambiarPassword(){
	
	if (clave.equals(StringUtils.EMPTY)|clave2.equals(StringUtils.EMPTY)) {
		mensajeError("Por favor digita los campos");
		return;
	}
	
	if (clave.equals(clave2)) {
		Usuario usuario = login.getUser();
		usuario.setContrasena(clave);
		usuarioService.save(usuario);
		mensaje("Realizado", "Se há cambiado tu clave personal");
	}else{
		mensajeError("Las contraseñas no coinciden");
		
	}
	
}



	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @return the clave2
	 */
	public String getClave2() {
		return clave2;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param clave2 the clave2 to set
	 */
	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/05/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
