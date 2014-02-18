/**
 * 
 */
package co.innovate.rentavoz.views.traslado.linea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLinea;
import co.innovate.rentavoz.model.traslado.linea.TrasladoLineaDetalle;
import co.innovate.rentavoz.services.linea.LineaService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.traslado.linea.TrasladoLineaDetalleService;
import co.innovate.rentavoz.services.traslado.linea.TrasladoLineaService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanTrasladoLinea
 * @date 17/02/2014
 * 
 */
@ManagedBean
@ViewScoped
public class BeanTrasladoLinea extends BaseBean implements Serializable {

	/**
	 * 17/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	

	@ManagedProperty(value = "#{lineaService}")
	private LineaService lineaService;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;

	@ManagedProperty(value="#{trasladoLineaService}")
	private TrasladoLineaService trasladoLineaService;
	
	@ManagedProperty(value="#{trasladoLineaDetalleService}")
	private TrasladoLineaDetalleService trasladoLineaDetalleService;
	
	private List<Linea> lineas = new ArrayList<Linea>();

	private Integer selSucursalOrigen;
	private Integer selSucursalDestino;

	private String numeroLinea;
	
	@ManagedProperty(value="#{login}")
	private Login login;

	
	@PostConstruct
	public void init(){
		lineas=new ArrayList<Linea>();
		numeroLinea=StringUtils.EMPTY;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 */
	public void cargarLinea() {
		Sucursal sucursal = sucursalService.findById(selSucursalOrigen);
		if (sucursal == null) {
			mensajeError("Por favor selecciona una sucursal");
			return;
		}
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		sucursales.add(sucursal);
		try {

			Linea linea = lineaService.findByNumeroObjeto(numeroLinea,
					sucursales);
			if (linea==null) {
				mensajeError("Linea no encontrada");
				return;
			}
			if (!lineas.contains(linea)) {
				lineas.add(linea);
			}else{
				mensajeError("Esta linea ya se encuentra en la lista");
				return;
			}
			numeroLinea=StringUtils.EMPTY;
		} catch (Exception e) {
			mensajeError(e.toString());
			return;
		}
	}
	
	public void removerLineaDeLista(Linea linea){
		lineas.remove(linea);
		
	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/02/2014
	*/
	public void trasladarLineas(){
		
		if (lineas.isEmpty()) {
			mensajeError("Selecciona por lo menos una linea para realizar el traslado");
			return;
		}
		if (selSucursalOrigen.equals(selSucursalDestino)) {
			mensajeError("La sucursal de destino debe ser diferente a la de origen");
			return;
		}
		Sucursal sucOrigen= sucursalService.findById(selSucursalOrigen);
		Sucursal sucursal= sucursalService.findById(selSucursalDestino);
		
		
		TrasladoLinea trasladoLinea = new  TrasladoLinea();
		trasladoLinea.setSucursalDestino(sucursal);
		trasladoLinea.setSucursalOrigen(sucOrigen);
		trasladoLinea.setFechaTraslado(Calendar.getInstance().getTime());
		trasladoLinea.setTercero(login.getTercero());
		TrasladoLinea tlt=trasladoLineaService.save(trasladoLinea);
		
		for (Linea linea : lineas) {
			TrasladoLineaDetalle trasladoLineaDetalle= new TrasladoLineaDetalle();
			trasladoLineaDetalle.setLinea(linea);
			trasladoLineaDetalle.setTraslado(tlt);
			trasladoLineaDetalleService.save(trasladoLineaDetalle);
			
			linea.setSucursal(sucursal);
			lineaService.save(linea);
		}
		
		init();
		mensaje("Realizado", "Se ha realizado el traslado de manera satisfactoria");
		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the lineas
	 */
	public List<Linea> getLineas() {
		return lineas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param selSucursalDestino
	 *            the selSucursalDestino to set
	 */
	public void setSelSucursalDestino(Integer selSucursalDestino) {
		this.selSucursalDestino = selSucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the selSucursalDestino
	 */
	public Integer getSelSucursalDestino() {
		return selSucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param selSucursalOrigen
	 *            the selSucursalOrigen to set
	 */
	public void setSelSucursalOrigen(Integer selSucursalOrigen) {
		this.selSucursalOrigen = selSucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the selSucursalOrigen
	 */
	public Integer getSelSucursalOrigen() {
		return selSucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the numeroLinea
	 */
	public String getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param numeroLinea
	 *            the numeroLinea to set
	 */
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the reglaNavegacion
	 */
	public  String reglaNavegacion() {
		return "/paginas/almacen/traslado/linea/index.jsf";
	}
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @param lineaService the lineaService to set
 */
public void setLineaService(LineaService lineaService) {
	this.lineaService = lineaService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @param trasladoLineaDetalleService the trasladoLineaDetalleService to set
 */
public void setTrasladoLineaDetalleService(
		TrasladoLineaDetalleService trasladoLineaDetalleService) {
	this.trasladoLineaDetalleService = trasladoLineaDetalleService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @param trasladoLineaService the trasladoLineaService to set
 */
public void setTrasladoLineaService(TrasladoLineaService trasladoLineaService) {
	this.trasladoLineaService = trasladoLineaService;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @param login the login to set
 */
public void setLogin(Login login) {
	this.login = login;
}
}
