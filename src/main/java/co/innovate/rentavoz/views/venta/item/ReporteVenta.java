/**
 * 
 */
package co.innovate.rentavoz.views.venta.item;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.venta.FacturaControllerService;
import co.innovate.rentavoz.services.venta.item.VentaItemService;
import co.innovate.rentavoz.views.ListaDataModel;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ReporteVenta
 * @date 22/01/2014
 *
 */
@ManagedBean
@SessionScoped
public class ReporteVenta implements Serializable {

	/**
	 * 22/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{ventaItemService}")
	private VentaItemService ventaItemService;

	@ManagedProperty(value="#{facturaControllerService}")
	private FacturaControllerService facturaControllerService;
	private Date start=new Date();
	
	private Date end= new Date();
	
	private VentaItem venta=new VentaItem();
	
	
	
	
	public void buscar(){
		model= new ListaDataModel<VentaItem, Integer>() {
			
			/**
			 * 22/01/2014
			 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public GenericService<VentaItem, Integer> getService() {
				return ventaItemService;
			}
			
		
			@Override
			public void customLoad(int startingAt, int maxPerPage,
					String globalFilter, String sortField, SortOrder sortOrder) {
				lista=(ventaItemService.findVentaByFechas(start, end, startingAt, maxPerPage));
				
			}

			@Override
			public Integer customCount(String globalFilter) {
				return ventaItemService.countFindVentaByFechas(start, end);
			}
		
		
		};
		
		
	}
	
	@PostConstruct
	public void init(){
//		model= new ListaDataModel<VentaItem, Integer>() {
//			
//			/**
//			 * 22/01/2014
//			 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//			 * serialVersionUID
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public GenericService<VentaItem, Integer> getService() {
//				return ventaItemService;
//			}
//			
//			/* (non-Javadoc)
//			 * @see co.innovate.rentavoz.views.ListaDataModel#customLoad(int, int)
//			 */
//			public void customLoad(int startingAt, int maxPerPage) {
//				setLista(ventaItemService.findVentaByFechas(start, end, startingAt,maxPerPage));
//				System.out.println(lista.size());
//				
//			}
//		
//		
//		};
		
	}
	
	
	public void loadFactura(VentaItem ventaItem){
		venta=facturaControllerService.cargarFacturaItem(ventaItem);
		
	}
	private ListaDataModel<VentaItem, Integer> model;
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @param ventaItemService the ventaItemService to set
	 */
	public void setVentaItemService(VentaItemService ventaItemService) {
		this.ventaItemService = ventaItemService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @return the ventaItemService
	 */
	public VentaItemService getVentaItemService() {
		return ventaItemService;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @return the model
	 */
	public ListaDataModel<VentaItem, Integer> getModel() {
		return model;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/01/2014
	 * @param model the model to set
	 */
	public void setModel(ListaDataModel<VentaItem, Integer> model) {
		this.model = model;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/06/2014
	 * @param facturaControllerService the facturaControllerService to set
	 */
	public void setFacturaControllerService(
			FacturaControllerService facturaControllerService) {
		this.facturaControllerService = facturaControllerService;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/06/2014
	 * @return the venta
	 */
	public VentaItem getVenta() {
		return venta;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/06/2014
	 * @param venta the venta to set
	 */
	public void setVenta(VentaItem venta) {
		this.venta = venta;
	}
}
	
