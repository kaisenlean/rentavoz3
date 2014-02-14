/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.ciudad;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.departamento.DepartamentoService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.buscador.BuscadorDepartamento;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanCiudad
 * @date 14/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanCiudad extends StandardAbm<Ciudad,Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{ciudadService}")
	private CiudadService ciudadService;
	
	@ManagedProperty(value="#{departamentoService}")
	private DepartamentoService departamentoService;

	private BuscadorDepartamento buscadorDepartamento;
	private Departamento departamento = new Departamento();

	@Override
	public GenericService<Ciudad,Integer> getFacade() {
		return ciudadService;
	}

	@Override
	public Ciudad getInstancia() {
		return new Ciudad();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/ciudad/index.jsf";
	}

	@Override
	public Ciudad getObjeto() {
		return obtenerObjeto();

	}

	@Override
	public List<Ciudad> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		buscadorDepartamento = new BuscadorDepartamento() {

			@Override
			public DepartamentoService getService() {
				return departamentoService;
			}

			@Override
			public void selCentrope(Departamento centrope) {
				if (getObjeto() != null) {
					departamento = centrope;
					getObjeto().setDepartamentoidDepartamento(centrope);
				}
			}
		};
	}

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
	public void preRenderizarItem() {
		departamento = getObjeto().getDepartamentoidDepartamento();
	}

	@Override
	public void buscarrPorCriterio() {

	}

	public BuscadorDepartamento getBuscadorDepartamento() {
		return buscadorDepartamento;
	}

	public void setBuscadorDepartamento(
			BuscadorDepartamento buscadorDepartamento) {
		this.buscadorDepartamento = buscadorDepartamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the ciudadService
	 */
	public CiudadService getCiudadService() {
		return ciudadService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param ciudadService the ciudadService to set
	 */
	public void setCiudadService(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the departamentoService
	 */
	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param departamentoService the departamentoService to set
	 */
	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String)
	 */
	@Override
	public List<Ciudad> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder) {
		return null;
	}
	
	

}
