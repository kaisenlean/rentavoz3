/**
 * 
 */
package co.innovate.rentavoz.model.cron;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CronActivity
 * @date 27/01/2014
 *
 */
@Entity
@Table(name="cron_activity")
public class CronActivity {

	@Id
	@Column
	private String id;
	
	@Temporal(TemporalType.TIME)
	@Column(name="hora_inicio")
	private Date horaInicio;
	

	@Temporal(TemporalType.TIME)
	@Column(name="hora_fin")
	private Date horaFin;
	
	
	 @Column(name="parametro")
	 private String parametro;
	 
	 @Column
	 private String ejecuciones;
	 
	 @Column(name="multi_ejecucion")
	 private Boolean multiEjecucion;
	 
	 
	 
	 
	 /**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 */
	public CronActivity() {
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the horaInicio
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the horaFin
	 */
	public Date getHoraFin() {
		return horaFin;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the ejecuciones
	 */
	public String getEjecuciones() {
		return ejecuciones;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param ejecuciones the ejecuciones to set
	 */
	public void setEjecuciones(String ejecuciones) {
		this.ejecuciones = ejecuciones;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the multiEjecucion
	 */
	public Boolean getMultiEjecucion() {
		return multiEjecucion;
	}




	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param multiEjecucion the multiEjecucion to set
	 */
	public void setMultiEjecucion(Boolean multiEjecucion) {
		this.multiEjecucion = multiEjecucion;
	}
	
	
	
}
