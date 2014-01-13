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
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
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
}
