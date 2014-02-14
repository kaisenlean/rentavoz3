/**
 * 
 */
package co.innovate.rentavoz.views.config.usuario;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

import co.innovate.rentavoz.logic.permiso.ControladorMenuUsuario;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.seguridad.EncoderManager;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.usuario.UsuarioService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanUsuario
 * @date 31/01/2014
 * 
 */
@ManagedBean
@ViewScoped
public class BeanUsuario extends StandardAbm<Usuario, String> implements
		Serializable {

	/**
	 * 31/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/config/usuario/index.jsf";

	/**
	 * 31/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;

	@ManagedProperty("#{encoderManager}")
	private EncoderManager encoderManager;
	
	@ManagedProperty(value="#{controladorMenuUsuario}")
	private ControladorMenuUsuario controladorMenuUsuario;

	private String password;

	private TreeNode[] seleccionados;
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	
	private TreeNode modelPermiso;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Usuario, String> getFacade() {
		return usuarioService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public Usuario getInstancia() {
		return new Usuario();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
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
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<Usuario> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public Usuario getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<Usuario> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		password = getObjeto().getContrasena();
		getObjeto().setContrasena2(password);
		modelPermiso=controladorMenuUsuario.cargarArbolPermisos(getObjeto());
	
	}
	
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		if (isEdit()) {
			
		if (getObjeto().getContrasena() == null
				|| getObjeto().getContrasena().equals("")) {
			getObjeto().setContrasena(password);
		}else{
			
			if (!getObjeto().getContrasena().equals(getObjeto().getContrasena2())) {
				mensajeError("Las claves no coinciden");
				return false;
			}
		}
		
	
		
		}
		if (!isEdit()) {
			Usuario temp = usuarioService.findById(getObjeto().getUsuario());
			if (temp != null) {
				mensajeError("El usuario ya se encuentra registrado en la base de datos por favor intenta con otro nombre de usuario");
				return false;
			}
			if (!getObjeto().getContrasena().equals(getObjeto().getContrasena2())) {
				mensajeError("Las claves no coinciden");
				return false;
			}
			return true;
		}
		return true;

	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {
		try {
			
			controladorMenuUsuario.guardarPermisos(modelPermiso,getObjeto());
		} catch (Exception e) {
			mensajeError(e.toString());
		}		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param usuarioService
	 *            the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param encoderManager
	 *            the encoderManager to set
	 */
	public void setEncoderManager(EncoderManager encoderManager) {
		this.encoderManager = encoderManager;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param controladorMenuUsuario the controladorMenuUsuario to set
	 */
	public void setControladorMenuUsuario(
			ControladorMenuUsuario controladorMenuUsuario) {
		this.controladorMenuUsuario = controladorMenuUsuario;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the modelPermiso
	 */
	public TreeNode getModelPermiso() {
		return modelPermiso;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param modelPermiso the modelPermiso to set
	 */
	public void setModelPermiso(TreeNode modelPermiso) {
		this.modelPermiso = modelPermiso;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the seleccionados
	 */
	public TreeNode[] getSeleccionados() {
		return seleccionados;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
