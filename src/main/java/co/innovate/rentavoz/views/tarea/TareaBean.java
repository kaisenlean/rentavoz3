/**
 * 
 */
package co.innovate.rentavoz.views.tarea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.tarea.TareaService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.SessionParams;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaBean
 * @date 15/03/2014
 * 
 */
@ManagedBean
@RequestScoped
public class TareaBean extends BaseBean implements Serializable {

	/**
	 * 15/03/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{tareaService}")
	private TareaService tareaService;

	@ManagedProperty(value = "#{login}")
	private Login login;

	private List<Tarea> tareas = new ArrayList<Tarea>();

	private Logger logger = Logger.getLogger(getClass());

	@PostConstruct
	public void init() {
		Centrope centrope = login.getTercero().getCentrope();
		if (centrope == null) {
			return;
		}
		try {

			if (centrope.getParametro().equals(ParametrosCentrope.OPERATIVO)) {
				tareas = tareaService
						.findTareasPendientes(TareaCentropeEnum.OPERATIVO);
				return;
			}

			if (centrope.getParametro().equals(
					ParametrosCentrope.ADMINISTRATIVO)) {
				tareas = tareaService
						.findTareasPendientes(TareaCentropeEnum.ADMINISTRATIVO);
				return;
			}

		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 16/03/2014
	* @param tarea
	*/
	public void loadTask(Tarea tarea) {

		addAttribute(SessionParams.ENTITY_BACK, tarea);
		goTo(tarea.getGoDir());

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param tareaService
	 *            the tareaService to set
	 */
	public void setTareaService(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the tareas
	 */
	public List<Tarea> getTareas() {
		return tareas;
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
}
