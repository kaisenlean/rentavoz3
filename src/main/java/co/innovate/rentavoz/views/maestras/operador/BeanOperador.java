/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.operador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.operador.OperadorService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BeanOperador
* @date 13/01/2014
*
 */
@ManagedBean
@ViewScoped
public class BeanOperador extends StandardAbm<Operador,Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{operadorService}")
	private OperadorService	 operadorService;

	@Override
	public GenericService<Operador,Integer> getFacade() {
		return operadorService;
	}

	@Override
	public Operador getInstancia() {
		return new Operador();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/operador/index.jsf";
	}

	@Override
	public Operador getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Operador> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {

	}

	@Override
	public void buscarrPorCriterio() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param operadorService the operadorService to set
	 */
	public void setOperadorService(OperadorService operadorService) {
		this.operadorService = operadorService;
	}
}
