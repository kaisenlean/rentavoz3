/**
 * 
 */
package co.innovate.rentavoz.model.facturacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class FechaFacturacion
 * @date 26/01/2014
 *
 */
@Entity
@Table(name="fecha_facturacion")
public class FechaFacturacion implements Serializable{

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column(name="fecha_inicio",unique=true)
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	

	@Column(name="fecha_fin",unique=true)
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	
	@Column
	private String label;
	
	@Column
	private Boolean activo;

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 */
	public FechaFacturacion() {
		activo=true;
	}


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 26/01/2014
	* @param id
	* @param fechaInicio
	* @param fechaFin
	* @param estado
	*/
	public FechaFacturacion(Integer id, Date fechaInicio, Date fechaFin,
			Boolean estado) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = estado;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the estado
	 */
	public Boolean getActivo() {
		return activo;
	}



	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param estado the estado to set
	 */
	public void setActivo(Boolean estado) {
		this.activo = estado;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  label ;
	}
	
	
	
	
	
}
