/**
 * 
 */
package co.innovate.rentavoz.views.venta.anular;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.innovate.rentavoz.logic.tarea.TareaController;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.model.venta.EstadoVentaItemEnum;
import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.services.venta.item.VentaItemService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class AnularVentaBean
 * @date 14/03/2014
 * 
 */
@ManagedBean
@ViewScoped
public class AnularVentaItemBean extends BaseBean implements Serializable {

	/**
	 * 14/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ventaItemService}")
	private VentaItemService ventaItemService;

	private VentaItem venta;

	private int idVenta;

	@ManagedProperty(value = "#{login}")
	private Login login;

	@ManagedProperty(value = "#{tareaController}")
	private TareaController tareaController;

	private boolean verAprobarAnulacion;

	private Tarea tarea = null;

	@PostConstruct
	public void init() {
		verAprobarAnulacion = false;
		Object sessionObject = getAttribute(SessionParams.ENTITY_BACK);
		removeAttribute(SessionParams.ENTITY_BACK);
		if (sessionObject != null) {

			tarea = (Tarea) sessionObject;

			if (tarea != null) {
				venta = ventaItemService.findById(Integer.valueOf(tarea
						.getCodTarea().toString()));
				idVenta = venta.getIdVenta();
			}
			verAprobarAnulacion = true;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 */
	public void loadVenta() {
		venta = ventaItemService.findById(idVenta);

		if (venta == null) {
			mensajeError("No se ha enconttrado esta factura de venta ");
			return;
		}
		mensaje("Relizado", "Factura de Venta Cargada");

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 */
	public void enviarSolicitud() {
		try {

			tareaController.crearTareaAnulacionVenta(venta, login.getTercero(),
					TareaCentropeEnum.ADMINISTRATIVO);
			mensaje("Realizado", "Solicitud de anulación enviada");
			venta = null;
		} catch (Exception e) {
			mensajeError("No se puedo realizar transaccion : " + (e.toString()));
		}

	}

	public void aprobarAnulacion() {

		venta.setFechaAnulacion(Calendar.getInstance().getTime());
		venta.setResponsableAnulacion(login.getTercero());
		venta.setEstado(EstadoVentaItemEnum.ANULADO);
		ventaItemService.save(venta);

		tareaController.finalizarTarea(tarea, login.getTercero());

		goTo("dashboard.jsf");

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(VentaItem venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param ventaItemService
	 *            the ventaItemService to set
	 */
	public void setVentaItemService(VentaItemService ventaItemService) {
		this.ventaItemService = ventaItemService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the ventaItemService
	 */
	public VentaItemService getVentaItemService() {
		return ventaItemService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the venta
	 */
	public VentaItem getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param idVenta
	 *            the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param tareaController
	 *            the tareaController to set
	 */
	public void setTareaController(TareaController tareaController) {
		this.tareaController = tareaController;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the tareaController
	 */
	public TareaController getTareaController() {
		return tareaController;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the verAprobarAnulacion
	 */
	public boolean isVerAprobarAnulacion() {
		return verAprobarAnulacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param verAprobarAnulacion
	 *            the verAprobarAnulacion to set
	 */
	public void setVerAprobarAnulacion(boolean verAprobarAnulacion) {
		this.verAprobarAnulacion = verAprobarAnulacion;
	}
}
