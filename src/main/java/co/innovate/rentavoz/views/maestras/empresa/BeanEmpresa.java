/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.empresa;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.empresa.EmpresaService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class BeanEmpresa extends StandardAbm<Empresa,Integer> implements Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/maestras/empresa/index.jsf";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6878840473274695937L;
	@ManagedProperty(value="#{empresaService}")
	private EmpresaService empresaService;

	@Override
	public GenericService<Empresa,Integer> getFacade() {
		return empresaService;
	}

	@Override
	public Empresa getInstancia() {
		return new Empresa();
	}

	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	@Override
	public Empresa getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Empresa> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {

	}

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
	public void buscarrPorCriterio() {
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param empresaService the empresaService to set
	 */
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
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
	public List<Empresa> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder) {
		return null;
	}
}
