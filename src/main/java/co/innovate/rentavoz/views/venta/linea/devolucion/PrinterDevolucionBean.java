/**
 * 
 */
package co.innovate.rentavoz.views.venta.linea.devolucion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.reports.PrinterBean;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PrinterDevolucionBean
 * @date 11/03/2014
 * 
 */
@ManagedBean
@RequestScoped
public class PrinterDevolucionBean extends BaseBean implements Serializable {

	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         FACTURA_INDIVIDUAL_LINEAS_DEVOLUCION
	 */
	private static final String FACTURA_INDIVIDUAL_LINEAS_DEVOLUCION = "facturaIndividual_lineas_devolucion";

	/**
	 * 11/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{printerBean}")
	private PrinterBean printerBean;

	@ManagedProperty(value = "#{opcionService}")
	private OpcionService opcionService;
	
	private Logger logger = Logger.getLogger(PrinterDevolucionBean.class);

	@SuppressWarnings("unchecked")
	public void init() {

		List<DevolucionDto> lista = ((List<DevolucionDto>) getAttribute(SessionParams.ENTITY_BACK));
		removeAttribute(SessionParams.ENTITY_BACK);
		HashMap<String, Object> mapa = new HashMap<String, Object>();
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

			opcion = opcionService.findByClave(OpcionConstants.NIT_EMPRESA);
			if (opcion == null) {
				mapa.put("NIT", "No parametrizada");
			} else {
				mapa.put("NIT", opcion.getValor());
			}

			opcion = opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA);
			if (opcion == null) {
				mapa.put("IMAGEN_EMPRESA", "");
			} else {
				mapa.put("IMAGEN_EMPRESA", "/" + opcion.getValor());
			}

			opcion = opcionService
					.findByClave(OpcionConstants.TELEFONOS_EMPRESA);
			if (opcion == null) {
				mapa.put("TELEFONO_EMPRESA", "");
			} else {
				mapa.put("TELEFONO_EMPRESA", opcion.getValor());
			}

		} catch (Exception e) {
			logger.error(e);
			mensajeError(e.toString());
		}
		printerBean.exportPdf(FACTURA_INDIVIDUAL_LINEAS_DEVOLUCION,
				"recibo_devolucion", mapa, lista);

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param printerBean
	 *            the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/03/2014
	 * @param opcionService
	 *            the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
}
