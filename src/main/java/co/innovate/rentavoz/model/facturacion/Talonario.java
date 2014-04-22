/**
 * 
 */
package co.innovate.rentavoz.model.facturacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.innovate.rentavoz.model.Sucursal;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class Talonario
 * @date 21/04/2014
 * 
 */
@Entity
@Table(name = "talonario")
public class Talonario implements Serializable {

	/**
	 * 21/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private String consecutivo;

	@Column
	private Boolean usado;

	@JoinColumn(name = "sucursal")
	@ManyToOne
	private Sucursal sucursal;

	@JoinColumn(name = "consecutivo_factura")
	@ManyToOne
	private ConsecutivoFactura consecutivoFactura;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 */
	public Talonario() {
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the consecutivo
	 */
	public String getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the usado
	 */
	public Boolean getUsado() {
		return usado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param usado the usado to set
	 */
	public void setUsado(Boolean usado) {
		this.usado = usado;
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
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the consecutivoFactura
	 */
	public ConsecutivoFactura getConsecutivoFactura() {
		return consecutivoFactura;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param consecutivoFactura the consecutivoFactura to set
	 */
	public void setConsecutivoFactura(ConsecutivoFactura consecutivoFactura) {
		this.consecutivoFactura = consecutivoFactura;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Talonario [consecutivo=" + consecutivo + ", usado=" + usado
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consecutivo == null) ? 0 : consecutivo.hashCode());
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
		Talonario other = (Talonario) obj;
		if (consecutivo == null) {
			if (other.consecutivo != null)
				return false;
		} else if (!consecutivo.equals(other.consecutivo))
			return false;
		return true;
	}
	
	
}
