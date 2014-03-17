/**
 * 
 */
package co.innovate.rentavoz.views.linea.tarea;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.log.linea.LogLineaService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.components.autocomplete.AutocompleteTercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OperacionLinea
 * @date 16/03/2014
 *
 */
@ManagedBean
@ViewScoped
public class LogLineaBean extends StandardAbm<LogLinea, Integer> implements Serializable {

	
	
	/**
	 * 16/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{logLineaService}")
	private LogLineaService logLineaService;
	
	@ManagedProperty(value="#{terceroService}")
	private TerceroService terceroService;
	
	
	private Tercero creador;
	
	
	
	private Date start;
	private Date end;
	
	private String numeroLinea=" ";
	
	private String accion;
	
	
	private AutocompleteTercero autocompleteTercero;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<LogLinea, Integer> getFacade() {
		return logLineaService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public LogLinea getInstancia() {
		return new LogLinea();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return null;
	}

	
	public void filtrar(){
		runJavascript("tablaLineas.filter();");
		
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		AccionLineaEnum accionLineaEnum=null;
		if (accion!=null) {
			if (!accion.equals(StringUtils.EMPTY)) {
				accionLineaEnum=AccionLineaEnum.valueOf(accion);
			}
		}
		return logLineaService.countByCriterio(start, end, numeroLinea, creador, accionLineaEnum);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<LogLinea> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		AccionLineaEnum accionLineaEnum=null;
		if (accion!=null) {
			if (!accion.equals(StringUtils.EMPTY)) {
				accionLineaEnum=AccionLineaEnum.valueOf(accion);
			}
		}
		return logLineaService.findByCriterio(start, end, numeroLinea, creador, accionLineaEnum,maxPerPage,startingAt,null);
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public LogLinea getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<LogLinea> getListado() {
		return obtenerListado();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		autocompleteTercero=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				creador=seleccionado;
			}
			
			@Override
			public TerceroService getService() {
				return terceroService;
			}
		};
	}
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @param logLineaService the logLineaService to set
 */
public void setLogLineaService(LogLineaService logLineaService) {
	this.logLineaService = logLineaService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the autocompleteTercero
 */
public AutocompleteTercero getAutocompleteTercero() {
	return autocompleteTercero;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param autocompleteTercero the autocompleteTercero to set
 */
public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
	this.autocompleteTercero = autocompleteTercero;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param terceroService the terceroService to set
 */
public void setTerceroService(TerceroService terceroService) {
	this.terceroService = terceroService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the accion
 */
public String getAccion() {
	return accion;
}
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param accion the accion to set
 */
public void setAccion(String accion) {
	this.accion = accion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the creador
 */
public Tercero getCreador() {
	return creador;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param creador the creador to set
 */
public void setCreador(Tercero creador) {
	this.creador = creador;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the start
 */
public Date getStart() {
	return start;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param start the start to set
 */
public void setStart(Date start) {
	this.start = start;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the end
 */
public Date getEnd() {
	return end;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param end the end to set
 */
public void setEnd(Date end) {
	this.end = end;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the numeroLinea
 */
public String getNumeroLinea() {
	return numeroLinea;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @param numeroLinea the numeroLinea to set
 */
public void setNumeroLinea(String numeroLinea) {
	this.numeroLinea = numeroLinea;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the logLineaService
 */
public LogLineaService getLogLineaService() {
	return logLineaService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/03/2014
 * @return the terceroService
 */
public TerceroService getTerceroService() {
	return terceroService;
}

}
