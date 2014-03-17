/**
 * 
 */
package co.innovate.rentavoz.views.linea.tarea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.linea.impl.LineaDaoImpl;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.log.linea.LogLineaService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class RegistrarLogLinea
 * @date 16/03/2014
 * 
 */
@ManagedBean
@ViewScoped
public class RegistrarLogLinea extends BaseBean implements Serializable {

	/**
	 * 16/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{logLineaService}")
	private LogLineaService logLineaService;

	private LogLinea logLinea = new LogLinea();

	private ArrayList<LogLinea> lineas = new ArrayList<LogLinea>();

	private String numeroLinea;

	private String tarea;

	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;
	
	@ManagedProperty(value="#{estadoLineaService}")
	private EstadoLineaService estadoLineaService;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 */
	public void addLinea() {
		Linea linea = lineaService.findBNumeroObjeto(numeroLinea);
		if (linea == null) {
			mensajeError("No se ha encontrado esta linea en el sistema");
			return;
		}
		
		logLinea=new LogLinea();
		logLinea.setAccion(AccionLineaEnum.valueOf(tarea));
		
		logLinea.setLinea(linea);
		logLinea.setFecha(Calendar.getInstance().getTime());
		logLinea.setUsuarioCrea(login.getTercero());
		
		lineas.add(logLinea);
		

	}

	
	public void guardar(){
		for (LogLinea logLinea : lineas) {
			logLineaService.save(logLinea);
			Linea linea=logLinea.getLinea();
			switch (logLinea.getAccion()) {
			case LOCAL:
				linea.setEstadoLineaidEstadoLinea(estadoLineaService.findById(LineaDaoImpl.ESTADO_LINEA_LOCAL));
				break;

			case SUSPENDER:
				linea.setEstadoLineaidEstadoLinea(estadoLineaService.findById(LineaDaoImpl.ESTADO_LINEA_SUSPENDIDA));
				break;
			default:
				break;
			}
			lineaService.save(linea);
			
		}
		lineas=new ArrayList<LogLinea>();
		numeroLinea=StringUtils.EMPTY;
		mensaje("Realizado", "Se han guardado las transacciones correctamente");
		
		
	}
	@ManagedProperty(value = "#{login}")
	private Login login;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param logLineaService
	 *            the logLineaService to set
	 */
	public void setLogLineaService(LogLineaService logLineaService) {
		this.logLineaService = logLineaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the lineas
	 */
	public ArrayList<LogLinea> getLineas() {
		return lineas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(ArrayList<LogLinea> lineas) {
		this.lineas = lineas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the logLinea
	 */
	public LogLinea getLogLinea() {
		return logLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param logLinea
	 *            the logLinea to set
	 */
	public void setLogLinea(LogLinea logLinea) {
		this.logLinea = logLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the numeroLinea
	 */
	public String getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param numeroLinea
	 *            the numeroLinea to set
	 */
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the tarea
	 */
	public String getTarea() {
		return tarea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param tarea
	 *            the tarea to set
	 */
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param lineaService
	 *            the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/03/2014
	 * @param estadoLineaService the estadoLineaService to set
	 */
	public void setEstadoLineaService(EstadoLineaService estadoLineaService) {
		this.estadoLineaService = estadoLineaService;
	}
}
