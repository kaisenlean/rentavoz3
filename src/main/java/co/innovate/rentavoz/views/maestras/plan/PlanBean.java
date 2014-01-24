/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.plan;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Operador;
import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.operador.OperadorService;
import co.innovate.rentavoz.services.plan.PlanService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.buscador.BuscadorOperador;
import co.innovate.rentavoz.views.components.buscador.BuscadorTercero;
import co.innovate.rentavoz.views.session.Login;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class PlanBean
* @date 13/01/2014
*
 */
@ManagedBean
@ViewScoped
public class PlanBean extends StandardAbm<Plan,Integer> {

	
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{planService}")
	private PlanService planService;
	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	@ManagedProperty(value="#{operadorService}")
	private OperadorService operadorService;
	
	
	private BuscadorTercero buscadorTercero;
	private BuscadorOperador buscadorOperador;
	private Tercero tercero;
	private Operador operador;
	@ManagedProperty(value = "#{login}")
	private Login login;

	@Override
	public GenericService<Plan,Integer> getFacade() {
		return planService;
	}

	@Override
	public Plan getInstancia() {
		return new Plan();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/plan/index.jsf";
	}

	@Override
	public Plan getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Plan> getListado() {
		return obtenerListado();
	}

	@Override
	public void postFormNuevo() {
		getObjeto().setFecha(new Date());
		getObjeto().setTerceroidTecero(login.getTercero());
		tercero = login.getTercero();
	}

	@Override
	public void initialize() {
		buscadorOperador = new BuscadorOperador() {
		

			@Override
			public void selCentrope(Operador centrope) {
				operador = centrope;
			}

			@Override
			public OperadorService getService() {
				return operadorService;
			}
		};
		buscadorTercero = new BuscadorTercero() {
			
			@Override
			public void selCentrope(Tercero centrope) {
				tercero = centrope;
			}

			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
	}

	@Override
	public void buscarrPorCriterio() {
	}

	@Override
	public boolean preAction() {

		if (operador == null || tercero == null) {
			StringBuilder builder = new StringBuilder("Por favor selecciona ");
			if (operador == null) {
				builder.append(" un  operador ");
			}
			if (tercero == null) {
				builder.append("y un tercero");
			}
			builder.append("para poder continuar.");
			mensaje("Error", builder.toString());
			return false;
		} else {
			getObjeto().setTerceroidTecero(tercero);
			getObjeto().setOperadoridOperador(operador);
			return true;
		}

	}

	@Override
	public void preRenderizarItem() {
		tercero = getObjeto().getTerceroidTecero();
		operador = getObjeto().getOperadoridOperador();
	}

	// <editor-fold defaultstate="collapsed" desc="CAPSULAS">
	public BuscadorTercero getBuscadorTercero() {
		return buscadorTercero;
	}

	public void setBuscadorTercero(BuscadorTercero buscadorTercero) {
		this.buscadorTercero = buscadorTercero;
	}

	public BuscadorOperador getBuscadorOperador() {
		return buscadorOperador;
	}

	public void setBuscadorOperador(BuscadorOperador buscadorOperador) {
		this.buscadorOperador = buscadorOperador;
	}

	public Tercero getTercero() {
		return tercero;
	}

	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the planService
	 */
	public PlanService getPlanService() {
		return planService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param planService the planService to set
	 */
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the terceroService
	 */
	public TerceroService getTerceroService() {
		return terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param terceroService the terceroService to set
	 */
	public void setTerceroService(TerceroService terceroService) {
		this.terceroService = terceroService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the operadorService
	 */
	public OperadorService getOperadorService() {
		return operadorService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param operadorService the operadorService to set
	 */
	public void setOperadorService(OperadorService operadorService) {
		this.operadorService = operadorService;
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
	public List<Plan> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField,
			SortOrder sortOrder) {
		return null;
	}
	
	
}
