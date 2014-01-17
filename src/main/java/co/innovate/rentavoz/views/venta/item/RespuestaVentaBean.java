/**
 * 
 */
package co.innovate.rentavoz.views.venta.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.reports.PrinterBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class RespuestaVentaBean
 * @date 17/01/2014
 *
 */
@ManagedBean
@RequestScoped
public class RespuestaVentaBean extends BaseBean implements Serializable {

	/**
	 * 17/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	private List<VentaItem> lista=new ArrayList<VentaItem>();
	public void loadReporte( ){
		
		Object object = getAttribute(SessionParams.ENTITY_BACK);
		
		if (object==null) {
			mensajeError("No se ha recibido información del servicio");
			goTo("/paginas/almacen/venta/item/");
		return;
		}
		VentaItem ventaItem = (VentaItem) object;
		 lista= new ArrayList<VentaItem>();
		lista.add(ventaItem);
		
		removeAttribute(SessionParams.ENTITY_BACK);
		
		printerBean.exportPdf("facturaIndividual_1", "factura_"+ventaItem.getIdVenta(),null,lista);
		
		
	}
	
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/01/2014
 * @param printerBean the printerBean to set
 */
public void setPrinterBean(PrinterBean printerBean) {
	this.printerBean = printerBean;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/01/2014
 * @return the lista
 */
public List<VentaItem> getLista() {
	return lista;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/01/2014
 * @param lista the lista to set
 */
public void setLista(List<VentaItem> lista) {
	this.lista = lista;
}
}
