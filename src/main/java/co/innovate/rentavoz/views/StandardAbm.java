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
import javax.servlet.ServletContext;

import org.hibernate.Hibernate;
import org.primefaces.model.SortOrder;

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
	
	}

	@PostConstruct
	public void init() {
	
		edit = false;
		Hibernate.initialize(objeto);
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
			
			/* (non-Javadoc)
			 * @see co.innovate.rentavoz.views.ListaDataModel#customLoad(int, int, java.lang.String)
			 */
			@Override
			public void customLoad(int startingAt, int maxPerPage,
					String globalFilter , String sortField,
					SortOrder sortOrder) {
			lista = customSearch(startingAt,maxPerPage,globalFilter,  sortField,
					 sortOrder);
			}
			
			
			/* (non-Javadoc)
			 * @see co.innovate.rentavoz.views.ListaDataModel#customCount(java.lang.String)
			 */
			@Override
			public Integer customCount(String globalFilter) {
				return custoCountBySearch(globalFilter);
			}

		

		};
		

		
		initialize();
		ctx = FacesContext.getCurrentInstance().getExternalContext();
		ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

	}
	public abstract Integer custoCountBySearch(String globalFilter);
	
	public abstract List<T> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder) ;

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
					objeto=getFacade().save(objeto);
					System.out.println(reglaNavegacion());
					mensaje("Hecho", "Se ha editado este objeto");
					postAction();
					return reglaNavegacion();
				} else {
					objeto=getFacade().save(objeto);
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
			
			return "";
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 */
	public void postAction() {

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
			this.objeto = objeto;
			preEliminar();
			getFacade().delete(objeto);
			init();
			postEliminar();
			mensaje("Hecho", "Se ha eliminado este objeto");
		} catch (Exception ex) {
			mensajeError(ex.toString());
			
		}
	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 23/01/2014
	*/
	public void preEliminar() {
		
	}

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


	/**
	 * 
	 * @return
	 */
	public boolean preAction() {
		return true;
	}

	public void preRenderizarItem() {
	}

	public abstract void initialize();


	public void postFormNuevo() {
	}

	public void postEliminar() {

		try {

		
			FacesContext.getCurrentInstance().getExternalContext().redirect(ctxPath + reglaNavegacion());

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
