/**
 * 
 */
package co.innovate.rentavoz.views.linea.tarea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.log.linea.AccionLineaEnum;
import co.innovate.rentavoz.model.log.linea.LogLinea;
import co.innovate.rentavoz.repositories.linea.impl.LineaDaoImpl;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.log.linea.LogLineaService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.session.Login;

import com.sun.faces.context.ExternalContextImpl;

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

	private List<LogLinea> lineas = new ArrayList<LogLinea>();

	private String numeroLinea;

	private String tarea;

	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;
	
	@ManagedProperty(value="#{estadoLineaService}")
	private EstadoLineaService estadoLineaService;
	
	private OutputStream out;
	
	private Logger logger = Logger.getLogger(RegistrarLogLinea.class);

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
	

	public void cargarRepos(FileUploadEvent event){
		try {
			
		
		InputStream in = event.getFile().getInputstream();
		String fileName = event.getFile().getFileName();
		fileName=event.getFile().getFileName().replace(" ", "").trim();

		ExternalContextImpl request;
		request = (ExternalContextImpl) FacesContext.getCurrentInstance()
				.getExternalContext();
		StringBuilder path = new StringBuilder( request.getRealPath("/"));
		out = new FileOutputStream(path
				+ fileName);
		 new File(request.getRealPath(path
				+ fileName));

		if (in != null) {
			int b = 0;
			while (b != -1) {
				b = in.read();
				if (b != -1) {
					out.write(b);

				}

			}
		}
		
		lineas=logLineaService.cargarLineaScid(path
				+ fileName,login.getTercero());
		} catch (Exception e) {
			logger.error(e);
		}
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
	public List<LogLinea> getLineas() {
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
