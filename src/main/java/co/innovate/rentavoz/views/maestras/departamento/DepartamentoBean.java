/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.departamento;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.departamento.DepartamentoService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class DepartamentoBean extends StandardAbm<Departamento,Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{departamentoService}")
	private DepartamentoService departamentoService;

	@Override
	public GenericService<Departamento,Integer> getFacade() {
		return departamentoService;
	}

	@Override
	public Departamento getInstancia() {
		return new Departamento();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/departamento/index.jsf";
	}

	@Override
	public Departamento getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Departamento> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		setListado(departamentoService.findAll());
	}

	@Override
	public void buscarrPorCriterio() {
	
	}

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
	public void postEliminar() {

		try {

			ctx.redirect(ctxPath + reglaNavegacion());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param departamentoService the departamentoService to set
	 */
	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the departamentoService
	 */
	public DepartamentoService getDepartamentoService() {
		return departamentoService;
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
	public List<Departamento> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder) {
		return null;
	}
}
