/**
 * 
 */
package co.innovate.rentavoz.views.reportes.linea.historial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.almacen.venta.VentaLinea;
import co.innovate.rentavoz.services.almacen.venta.linea.VentaLineaService;
import co.innovate.rentavoz.services.linea.LineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanHistorialFacturacion
 * @date 4/03/2014
 *
 */
@ManagedBean
@ViewScoped
public class BeanHistorialFacturacion implements Serializable {

	/**
	 * 4/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{ventaLineaService}")
	private VentaLineaService ventaLineaService;
	
	
	
	private List<VentaLinea> historia =new ArrayList<VentaLinea>();
	
	private String lineaTxt;
	private Linea linea;
	
	
	@ManagedProperty(value="#{lineaService}")
	private LineaService lineaService;
	
	@PostConstruct
	public void init(){
		lineaTxt=StringUtils.EMPTY;
		linea=null;
		
	}


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/03/2014
	*/
	public void cargarHistorial(){
		linea=lineaService.getByNumeroLinea(lineaTxt);
		historia=ventaLineaService.findHistorialFacturacion(linea);
		
		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the ventaLineaService
	 */
	public VentaLineaService getVentaLineaService() {
		return ventaLineaService;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param ventaLineaService the ventaLineaService to set
	 */
	public void setVentaLineaService(VentaLineaService ventaLineaService) {
		this.ventaLineaService = ventaLineaService;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the historia
	 */
	public List<VentaLinea> getHistoria() {
		return historia;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param historia the historia to set
	 */
	public void setHistoria(List<VentaLinea> historia) {
		this.historia = historia;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the lineaTxt
	 */
	public String getLineaTxt() {
		return lineaTxt;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param lineaTxt the lineaTxt to set
	 */
	public void setLineaTxt(String lineaTxt) {
		this.lineaTxt = lineaTxt;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the linea
	 */
	public Linea getLinea() {
		return linea;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param linea the linea to set
	 */
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param lineaService the lineaService to set
	 */
	public void setLineaService(LineaService lineaService) {
		this.lineaService = lineaService;
	}

}
