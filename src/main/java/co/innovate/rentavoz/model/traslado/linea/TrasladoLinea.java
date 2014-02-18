/**
 * 
 */
package co.innovate.rentavoz.model.traslado.linea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TrasladoLinea
 * @date 17/02/2014
 *
 */
@Entity
@Table(name="traslado_linea")
public class TrasladoLinea  implements Serializable{

	/**
	 * 17/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_traslado")
	private Date fechaTraslado;
	
	@ManyToOne
	@JoinColumn(name="sucursal_origen")
	private Sucursal sucursalOrigen;
	

	@ManyToOne
	@JoinColumn(name="sucursal_destino")
	private Sucursal sucursalDestino;
	
	@ManyToOne
	@JoinColumn(name="tercero_registro")
	private Tercero tercero;
	
	@Transient
	private List<TrasladoLineaDetalle> detalles = new ArrayList<TrasladoLineaDetalle>();
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 */
	public TrasladoLinea() {
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the fechaTraslado
	 */
	public Date getFechaTraslado() {
		return fechaTraslado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param fechaTraslado the fechaTraslado to set
	 */
	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the sucursalOrigen
	 */
	public Sucursal getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param sucursalOrigen the sucursalOrigen to set
	 */
	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the sucursalDestino
	 */
	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param sucursalDestino the sucursalDestino to set
	 */
	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @return the detalles
	 */
	public List<TrasladoLineaDetalle> getDetalles() {
		return detalles;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/02/2014
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List<TrasladoLineaDetalle> detalles) {
		this.detalles = detalles;
	}
	
	
}
