/**
 * 
 */
package co.innovate.rentavoz.views.config;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.cron.CronActivity;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.cron.CronActivityService;
import co.innovate.rentavoz.views.StandardAbm;


/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BeanCronActivity
* @date 27/01/2014
*
*/
@ManagedBean
@ViewScoped
public class BeanCronActivity extends StandardAbm<CronActivity, String> implements Serializable {

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SEMI_COLON
	 */
	private static final String SEMI_COLON = ";";

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{cronActivityService}")
	private CronActivityService cronActivityService;
	
	private Date hora;
	
	private DateFormat format=new SimpleDateFormat("HH:mm");
	
	private ArrayList<String> crons= new ArrayList<String>();
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<CronActivity, String> getFacade() {
		return cronActivityService;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public CronActivity getInstancia() {
		return new CronActivity();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/config/cronActivity/index.jsf";
	}
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	*/
	public void addToList(){
		if (hora==null) {
			mensajeError("Por favor selecciona una hora válida");
			return;
		}
		String hour= format.format(hora);
		if (crons.contains(hour)) {
			mensajeError("Ya está en la lista esta hora");
			return;
		}
		crons.add(hour);
		hora = new Date();
		
		
	}
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	* @param hour
	*/
	public void removeToList(String hour){
		crons.remove(hour);
		
	}
	
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {

		if (getObjeto().getMultiEjecucion()==null) {
			return;
		}
		
		if (getObjeto().getMultiEjecucion()) {
			String jobs= getObjeto().getEjecuciones();
			if (jobs==null) {
				return;
			}
			
			if (crons==null) {
				crons=new ArrayList<String>();
				return;
			}
			
			
			crons=new ArrayList<String>( Arrays.asList(getObjeto().getEjecuciones().split(SEMI_COLON)));
			
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {

	if (getObjeto().getMultiEjecucion()) {
		if (crons.isEmpty()) {
			mensajeError("Por favor adiciona las horas a ejecutar la tarea");
			return false;
		}
		StringBuffer buffer= new StringBuffer();
		for (String cron : crons) {
			buffer.append(cron);
			buffer.append(SEMI_COLON);
		}
		getObjeto().setEjecuciones(buffer.toString());
		return true;
	}else{
		
		if (getObjeto().getHoraInicio()==null) {
			mensajeError("Por favor selecciona la hora a ejecutar la tarea");
			return false;
		}
		return true;
	}
	
	
	
	}
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String)
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int, java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<CronActivity> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public CronActivity getObjeto() {
		return obtenerObjeto();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<CronActivity> getListado() {
		return obtenerListado();
	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
	crons= new ArrayList<String>();	
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param cronActivityService the cronActivityService to set
	 */
	public void setCronActivityService(CronActivityService cronActivityService) {
		this.cronActivityService = cronActivityService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param crons the crons to set
	 */
	public void setCrons(ArrayList<String> crons) {
		this.crons = crons;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the crons
	 */
	public List<String> getCrons() {
		return crons;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the hora
	 */
	public Date getHora() {
		return hora;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param hora the hora to set
	 */
	public void setHora(Date hora) {
		this.hora = hora;
	}
}
