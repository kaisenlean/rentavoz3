/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.maestras.sucursal;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.ciudad.CiudadService;
import co.innovate.rentavoz.services.departamento.DepartamentoService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.buscador.BuscadorCiudad;
import co.innovate.rentavoz.views.components.buscador.BuscadorDepartamento;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class BeanSucursal extends StandardAbm<Sucursal,Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{sucursalService}")
	private SucursalService sucursalService;
	
	@ManagedProperty(value="#{ciudadService}")
	private CiudadService ciudadService;
	
	@ManagedProperty(value="#{departamentoService}")
	private DepartamentoService departamentoService;
	
	private BuscadorDepartamento buscadorDepartamento;
	private BuscadorCiudad buscadorCiudad;
	private Departamento departamento = new Departamento();
	private Ciudad ciudad = new Ciudad();
	private boolean verCiudad = false;

	@Override
	public GenericService<Sucursal,Integer> getFacade() {
		return sucursalService;
	}

	@Override
	public Sucursal getInstancia() {
		return new Sucursal();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/sucursal/index.jsf";
	}

	@Override
	public Sucursal getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Sucursal> getListado() {
		return obtenerListado();
	}

	@Override
	public void preRenderizarItem() {
		if (getObjeto().getCiudadidCiudad() != null) {
			ciudad = getObjeto().getCiudadidCiudad();
			departamento = ciudad.getDepartamentoidDepartamento();
			verCiudad = true;
		}

	}

	@Override
	public boolean preAction() {
		return true;

	}

	@Override
	public void initialize() {
		buscadorDepartamento = new BuscadorDepartamento() {
			

			@Override
			public void selCentrope(Departamento centrope) {
				departamento = centrope;
				verCiudad = true;

			}

			@Override
			public DepartamentoService getService() {
				return departamentoService;
			}
		};
		buscadorCiudad = new BuscadorCiudad() {
			

			@Override
			public Departamento getDepartamento() {
				return departamento;
			}

			@Override
			public void selCentrope(Ciudad centrope) {
				ciudad = centrope;
				getObjeto().setCiudadidCiudad(ciudad);
			}

			@Override
			public CiudadService getService() {
				return ciudadService;
			}
		};
	}

	@Override
	public void buscarrPorCriterio() {
	}

	// <editor-fold defaultstate="collapsed" desc="Capsulas">

	public BuscadorDepartamento getBuscadorDepartamento() {
		return buscadorDepartamento;
	}

	public void setBuscadorDepartamento(
			BuscadorDepartamento buscadorDepartamento) {
		this.buscadorDepartamento = buscadorDepartamento;
	}

	public BuscadorCiudad getBuscadorCiudad() {
		return buscadorCiudad;
	}

	public void setBuscadorCiudad(BuscadorCiudad buscadorCiudad) {
		this.buscadorCiudad = buscadorCiudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isVerCiudad() {
		return verCiudad;
	}

	public void setVerCiudad(boolean verCiudad) {
		this.verCiudad = verCiudad;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @return the sucursalService
	 */
	public SucursalService getSucursalService() {
		return sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param sucursalService the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
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
	
	
	// </editor-fold>
}
