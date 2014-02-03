/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "sucursaltercero")
public class SucursalTercero implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSucursalTercero")
	private Integer idSucursalTercero;
	
	@Column(name = "sucTerEstado")
	private int sucTerEstado;
	
	
	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne
	private Tercero terceroidTecero;
	
	
	@JoinColumn(name = "Sucursal_idSucursal", referencedColumnName = "idSucursal")
	@ManyToOne
	private Sucursal sucursalidSucursal;
	
	public SucursalTercero() {
	}

	public SucursalTercero(Integer idSucursalTercero) {
		this.idSucursalTercero = idSucursalTercero;
	}

	public SucursalTercero(Integer idSucursalTercero, int sucTerEstado) {
		this.idSucursalTercero = idSucursalTercero;
		this.sucTerEstado = sucTerEstado;
	}

	public Integer getIdSucursalTercero() {
		return idSucursalTercero;
	}

	public void setIdSucursalTercero(Integer idSucursalTercero) {
		this.idSucursalTercero = idSucursalTercero;
	}

	public int getSucTerEstado() {
		return sucTerEstado;
	}

	public void setSucTerEstado(int sucTerEstado) {
		this.sucTerEstado = sucTerEstado;
	}

	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
	}

	public Sucursal getSucursalidSucursal() {
		return sucursalidSucursal;
	}

	public void setSucursalidSucursal(Sucursal sucursalidSucursal) {
		this.sucursalidSucursal = sucursalidSucursal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((sucursalidSucursal == null) ? 0 : sucursalidSucursal
						.hashCode());
		result = prime * result
				+ ((terceroidTecero == null) ? 0 : terceroidTecero.hashCode());
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
		SucursalTercero other = (SucursalTercero) obj;
		if (sucursalidSucursal == null) {
			if (other.sucursalidSucursal != null)
				return false;
		} else if (!sucursalidSucursal.equals(other.sucursalidSucursal))
			return false;
		if (terceroidTecero == null) {
			if (other.terceroidTecero != null)
				return false;
		} else if (!terceroidTecero.equals(other.terceroidTecero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.SucursalTercero[ idSucursalTercero="
				+ idSucursalTercero + " ]";
	}

}
