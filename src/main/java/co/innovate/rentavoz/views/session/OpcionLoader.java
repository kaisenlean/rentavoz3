/**
 * 
 */
package co.innovate.rentavoz.views.session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.BaseBean;

import com.sun.faces.context.ExternalContextImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionLoader
 * @date 18/01/2014
 * 
 */
@ManagedBean
@ViewScoped
public class OpcionLoader extends BaseBean implements Serializable {

	private String maxRegistrosPagina;
	private String numRows;
	private String imagenEmpresa;
	/**
	 * 18/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{opcionService}")
	private OpcionService opcionService;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 */
	@PostConstruct
	public void init() {
		try {

			maxRegistrosPagina = opcionService.findByClave(
					OpcionConstants.MAXIMO_REGISTROS_POR_PAGINA).getValor();
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		try {
			numRows = opcionService.findByClave(
					OpcionConstants.NUMERO_REGISTROS_INICIAL).getValor();
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		
		try {
			imagenEmpresa = opcionService.findByClave(
					OpcionConstants.IMAGEN_EMPRESA).getValor();
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		
		

	}
	
	@SuppressWarnings("resource")
	public void uploadHandler(FileUploadEvent event) {
		try {
			
			Opcion opcion= opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA);
			InputStream in = event.getFile().getInputstream();
			InputStream in2 = event.getFile().getInputstream();
			String fileName = event.getFile().getFileName();
			fileName=event.getFile().getFileName().replace(" ", "").trim();

			ExternalContextImpl request;
			request = (ExternalContextImpl) FacesContext.getCurrentInstance()
					.getExternalContext();
			StringBuilder path = new StringBuilder( request.getRealPath("/"));
			path.append("imagenes/");
			OutputStream out = new FileOutputStream(path
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
			
			StringBuilder path2 = new StringBuilder( request.getRealPath("/"));
			path2.append("reportes/");
			OutputStream out2 = new FileOutputStream(path2
					+ fileName);
			 new File(request.getRealPath(path2
					+ fileName));

			if (in2 != null) {
				int b = 0;
				while (b != -1) {
					b = in2.read();
					if (b != -1) {
						out2.write(b);

					}

				}
			}

			opcion.setValor(fileName);
			opcionService.save(opcion);
			init();
			mensaje("Realizado", "Carga de fotos realizada");

		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}
	

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @return the maxRegistrosPagina
	 */
	public String getMaxRegistrosPagina() {
		return maxRegistrosPagina;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @param maxRegistrosPagina
	 *            the maxRegistrosPagina to set
	 */
	public void setMaxRegistrosPagina(String maxRegistrosPagina) {
		this.maxRegistrosPagina = maxRegistrosPagina;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @param opcionService
	 *            the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @return the numRows
	 */
	public String getNumRows() {
		return numRows;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @param numRows the numRows to set
	 */
	public void setNumRows(String numRows) {
		this.numRows = numRows;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @return the imagenEmpresa
	 */
	public String getImagenEmpresa() {
		return imagenEmpresa;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @param imagenEmpresa the imagenEmpresa to set
	 */
	public void setImagenEmpresa(String imagenEmpresa) {
		this.imagenEmpresa = imagenEmpresa;
	}
	
	

}
