/**
 * 
 */
package co.innovate.rentavoz.views.consumo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import co.innovate.rentavoz.logic.consumo.ConsumoControllerService;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.views.BaseBean;

import com.sun.faces.context.ExternalContextImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanConsumo
 * @date 19/02/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanConsumo extends BaseBean implements Serializable{

	/**
	 * 19/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{consumoControllerService}")
	private ConsumoControllerService consumoControllerService;
	
	
	private List<LineaConsumo> consumos;
	
	
	@ManagedProperty(value="#{lineaConsumoService}")
	private LineaConsumoService lineaConsumoService;
	
	@ManagedProperty(value="#{lineaService}")
	private LineaService lineaService;
	
	private Logger logger = Logger.getLogger(BeanConsumo.class);

	private OutputStream out;
	
	private String numeroLinea=StringUtils.EMPTY;

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 19/02/2014
	*/
	@PostConstruct
	public void init(){
		consumos=new ArrayList<LineaConsumo>();
	
	}
	
	
	public void buscarConsumoLinea(){
		
		if (numeroLinea.equals(StringUtils.EMPTY)) {
			mensajeError("Por favor digita el número de la linea");
			return;
		}
		
		Linea linea = lineaService.findBNumeroObjeto(numeroLinea);
		
		if (linea==null) {
			mensajeError("Linea no encontrada en el sistema");
			return;
		}
		
		
		consumos=lineaConsumoService.findByLinea(linea);
		if (consumos.isEmpty()) {
			mensaje("Realizado", "No se han encontrado consumos registrados en el sistema para la linea : ".concat(linea.getLinNumero()));
		}
		
	}
	
	public void cargarConsumosClaro(FileUploadEvent event){
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
		
		consumos=consumoControllerService.cargarConsumosClaro(path
				+ fileName);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public void guardarConsumos(){
		for (LineaConsumo consumo : consumos) {
			lineaConsumoService.save(consumo);
		}
		init();
		mensaje("Realizado", "Se han subido los consumos a la plataforma correctamente");
		
	}
	

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/02/2014
	 * @return the consumos
	 */
	public List<LineaConsumo> getConsumos() {
		return consumos;
	}





	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/02/2014
	 * @param consumos the consumos to set
	 */
	public void setConsumos(List<LineaConsumo> consumos) {
		this.consumos = consumos;
	}





	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/02/2014
	 * @param consumoControllerService the consumoControllerService to set
	 */
	public void setConsumoControllerService(
			ConsumoControllerService consumoControllerService) {
		this.consumoControllerService = consumoControllerService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 20/02/2014
	 * @param lineaConsumoService the lineaConsumoService to set
	 */
	public void setLineaConsumoService(LineaConsumoService lineaConsumoService) {
		this.lineaConsumoService = lineaConsumoService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 20/02/2014
	 * @return the numeroLinea
	 */
	public String getNumeroLinea() {
		return numeroLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 20/02/2014
	 * @param numeroLinea the numeroLinea to set
	 */
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 20/02/2014
	 * @param lineaService the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 20/02/2014
	 * @return the lineaConsumoService
	 */
	public LineaConsumoService getLineaConsumoService() {
		return lineaConsumoService;
	}

}
