/**
 * 
 */
package co.innovate.rentavoz.model.bodega;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventario
 * @date 7/04/2014
 *
 */
@Entity
 @Table(name="tipo_inventario")
public class TipoInventario implements Serializable{

	/**
	 * 7/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="incluye_serial")
	private boolean incluyeSerial;

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 7/04/2014
	* @param nombre
	* @param clave
	*/
	public TipoInventario(String nombre, String clave) {
		super();
		this.nombre = nombre;
		this.clave = clave;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 */
	public TipoInventario() {
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/06/2014
	 * @return the incluyeSerial
	 */
	public boolean isIncluyeSerial() {
		return incluyeSerial;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/06/2014
	 * @param incluyeSerial the incluyeSerial to set
	 */
	public void setIncluyeSerial(boolean incluyeSerial) {
		this.incluyeSerial = incluyeSerial;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoInventario other = (TipoInventario) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoInventario [id=" + id + ", nombre=" + nombre + "]";
	}

	
	

}
