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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsecutivoFactura
 * @date 21/04/2014
 * 
 */
@Entity
@Table(name = "consecutivo_factura")
public class ConsecutivoFactura implements Serializable {

	/**
	 * 21/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String letra;

	@Column
	private Integer consecutivo;
	
	@Column(name="consecutivo_final")
	private Integer consecutivoFinal;
	
	@JoinColumn(name = "usuario_crea")
	@ManyToOne
	private Tercero usuarioCrea;

	@JoinColumn(name = "usuario_modifica")
	@ManyToOne
	private Tercero usuarioModifica;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;

	@ManyToOne
	@JoinColumn(name = "sucursal")
	private Sucursal sucursal;
	
	
	@Column
	private Boolean activo;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 */
	public ConsecutivoFactura() {
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the consecutivo
	 */
	public Integer getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param consecutivo
	 *            the consecutivo to set
	 */
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the letra
	 */
	public String getLetra() {
		return letra;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param letra
	 *            the letra to set
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the usuarioCrea
	 */
	public Tercero getUsuarioCrea() {
		return usuarioCrea;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the usuarioModifica
	 */
	public Tercero getUsuarioModifica() {
		return usuarioModifica;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param usuarioCrea the usuarioCrea to set
	 */
	public void setUsuarioCrea(Tercero usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param usuarioModifica the usuarioModifica to set
	 */
	public void setUsuarioModifica(Tercero usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the consecutivoFinal
	 */
	public Integer getConsecutivoFinal() {
		return consecutivoFinal;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param consecutivoFinal the consecutivoFinal to set
	 */
	public void setConsecutivoFinal(Integer consecutivoFinal) {
		this.consecutivoFinal = consecutivoFinal;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsecutivoFactura [letra=" + letra + ", consecutivo="
				+ consecutivo + "]";
	}

}
