/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.innovate.rentavoz.services.GenericService;

/**
 * 
 * @author ejody
 */
public abstract class StandardAbm<T,PK extends Serializable> extends BaseBean implements Serializable,
		StandardInterface<T,PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * variable para manipular el formulario y su vista
	 */
	protected boolean form = false;
	private T objeto;
	private String navigationRule;
	private String criterio;
	private List<T> listado;
	protected ExternalContext ctx;
	protected String ctxPath;
	protected FacesContext context;
	protected String reglaNavegacionAlterna;
	private ListaDataModel< T,PK> model;

	public StandardAbm() {
	}

	public abstract GenericService<T,PK > getFacade();

	public abstract T getInstancia();

	public abstract String reglaNavegacion();

	/**
	 * 
	 * @return
	 */
	/**
	 * 
	 * @param facade
	 * @param dirPagina
	 */
	public StandardAbm(GenericService<T,PK> facade, String dirPagina) {
		// this.getFacade() = facade;
		// this.dirPagina=dirPagina;
		// t.getJavaType().g
	}

	@PostConstruct
	public void init() {
		edit = false;
		listado = getFacade().findAll();
		model=new ListaDataModel<T, PK>() {

			/**
			 * 13/01/2014
			 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public GenericService<T, PK> getService() {
				return getFacade();
			}
		};
		initialize();
		ctx = FacesContext.getCurrentInstance().getExternalContext();
//		ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

	}

	/**
	 * variable para manipular el campo editar
	 */
	private boolean edit = false;

	/**
	 * form
	 */

	public void verForm() {
		form = !form;
	}

	public void renderizarItem(T objeto, boolean showForm) {
		edit = true;
		this.objeto = objeto;
		preRenderizarItem();
		if (showForm) {
			verForm();
		}

	}

	public String aceptar() {

		try {

			if (preAction()) {

				if (edit) {
					getFacade().save(objeto);
//					RequestContext
//							.getCurrentInstance()
//							.execute(
//									"alert('Se ha realizado la transaccion correctamente !!');");
					// FacesUtils.mensaje("Se ha realizado la transaccion correctamente !!");
					System.out.println(reglaNavegacion());
					mensaje("Hecho", "Se ha editado este objeto");
					postAction();
					return reglaNavegacion();
				} else {
					getFacade().save(objeto);
//					RequestContext
//							.getCurrentInstance()
//							.execute(
//									"alert('Se ha realizado la transaccion correctamente !!');");
					// FacesUtils.mensaje("Se ha realizado la transaccion correctamente !!");
					System.out.println(reglaNavegacion());
					mensaje("Hecho", "Se ha creado un nuevo objeto");
					postAction();
					if (reglaNavegacionAlterna!=null) {
						return reglaNavegacionAlterna;
					}
					return reglaNavegacion();
				}

			} else {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensajeError(e.toString());
			// FacesUtils.mensaje("No se ha finalizado la transaccion con exito . Respuesta del servidor : "
			// + e.toString());
			return "";
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 */
	public void postAction() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void verFormNuevo(boolean showForm) {
		edit = false;
		try {

			objeto = (T) getInstancia().getClass().newInstance();

			postFormNuevo();
		} catch (InstantiationException ex) {
			Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE,
					null, ex);
			System.err.println("Error al inicializar la clase");
		} catch (IllegalAccessException ex) {
			Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE,
					null, ex);
			System.err.println("Error al inicializar la clase");
		}
		if (showForm) {
			verForm();

		}
	}

	public void eliminarItem(T objeto) {
		try {
			System.out.println("ando aca");
			this.objeto = objeto;
			getFacade().save(objeto);
			init();
			postEliminar();
			mensaje("Hecho", "Se ha eliminado este objeto");
			// FacesUtils.mensaje("Se  ha eiminado este objeto");
		} catch (Exception ex) {
			mensaje("Error",
					"NO se puede eliminar este objeto debido a que tiene referencias con otros items");
			Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE,
					null, ex);
			// FacesUtils.mensaje("No se puede eliminar el objeto debido a que tiene  referencias asocidas");
		}
		// return reglaNavegacion();
	}

	// <editor-fold defaultstate="collapsed" desc="CAPSULAS">
	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getNavigationRule() {
		return navigationRule;
	}

	public void setNavigationRule(String navigationRule) {
		this.navigationRule = navigationRule;
	}

	public boolean isForm() {
		return form;
	}

	public void setForm(boolean form) {
		this.form = form;
	}

	public abstract T getObjeto();

	public T obtenerObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public abstract List<T> getListado();

	public List<T> obtenerListado() {
		return listado;
	}

	public void setListado(List<T> listado) {
		this.listado = listado;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	// </editor-fold>

	/**
	 * 
	 * @return
	 */
	public boolean preAction() {
		return true;
	}

	public void preRenderizarItem() {
		// throw new UnsupportedOperationException("No soportado");
	}

	public abstract void initialize();

	// throw new UnsupportedOperationException("No soportado");

	public void postFormNuevo() {
	}

	public void postEliminar() {

		try {

			// Usar el contexto de JSF para invalidar la sesión,
			// NO EL DE SERVLETS (nada de HttpServletRequest)
			// ((HttpSession) ctx.getSession(false)).invalidate();

			// Redirección de nuevo con el contexto de JSF,
			// si se usa una HttpServletResponse fallará.
			// Sin embargo, como ya está fuera del ciclo de vida
			// de JSF se debe usar la ruta completa -_-U
			ctx.redirect(ctxPath + reglaNavegacion());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void mensaje(String title, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(title, mensaje));
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/06/2013
	 */
	public void buscarrPorCriterio() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the model
	 */
	public ListaDataModel<T, PK> getModel() {
		return model;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param model the model to set
	 */
	public void setModel(ListaDataModel<T, PK> model) {
		this.model = model;
	}
}
