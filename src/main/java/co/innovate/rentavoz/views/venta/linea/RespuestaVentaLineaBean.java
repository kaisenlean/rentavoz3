/**
 * 
 */
package co.innovate.rentavoz.views.venta.linea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class RespuestaVentaBean
 * @date 17/01/2014
 * 
 */
@ManagedBean
@RequestScoped
public class RespuestaVentaLineaBean extends BaseBean implements Serializable {

	/**
	 * 17/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{printerBean}")
	private PrinterBean printerBean;

	@ManagedProperty(value = "#{opcionService}")
	private OpcionService opcionService;

	private List<Venta> lista = new ArrayList<Venta>();

	public void loadReporte() {

		Object object = getAttribute(SessionParams.ENTITY_BACK);
		removeAttribute(SessionParams.ENTITY_BACK);
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		if (object == null) {
			mensajeError("No se ha recibido información del servicio");
			goTo("/paginas/almacen/venta/item/");
			return;
		}
		Venta ventaItem = (Venta) object;
		lista = new ArrayList<Venta>();
		lista.add(ventaItem);


		try {

			Opcion opcion = opcionService
					.findByClave(OpcionConstants.RAZON_SOCIAL_EMPRESA);
			if (opcion == null) {
				mapa.put("RAZON_SOCIAL", "No parametrizada");
			} else {
				mapa.put("RAZON_SOCIAL", opcion.getValor());
			}
			
			 opcion = opcionService
					.findByClave(OpcionConstants.DIRECCION_EMPRESA);
			if (opcion == null) {
				mapa.put("DIRECCION_EMPRESA", "No parametrizada");
			} else {
				mapa.put("DIRECCION_EMPRESA", opcion.getValor());
			}
			

			 opcion = opcionService
					.findByClave(OpcionConstants.NIT_EMPRESA);
			if (opcion == null) {
				mapa.put("NIT", "No parametrizada");
			} else {
				mapa.put("NIT", opcion.getValor());
			}
			
			 opcion = opcionService
						.findByClave(OpcionConstants.IMAGEN_EMPRESA);
				if (opcion == null) {
					mapa.put("IMAGEN_EMPRESA", "");
				} else {
					mapa.put("IMAGEN_EMPRESA", "/"+opcion.getValor());
				}
				
				

				 opcion = opcionService
							.findByClave(OpcionConstants.TELEFONOS_EMPRESA);
					if (opcion == null) {
						mapa.put("TELEFONO_EMPRESA", "");
					} else {
						mapa.put("TELEFONO_EMPRESA", opcion.getValor());
					}
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		printerBean.exportPdf("facturaIndividual_lineas",
				"factura_lineas_" + ventaItem.getIdVenta(), mapa, lista);

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param printerBean
	 *            the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the lista
	 */
	public List<Venta> getLista() {
		return lista;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<Venta> lista) {
		this.lista = lista;
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
}
