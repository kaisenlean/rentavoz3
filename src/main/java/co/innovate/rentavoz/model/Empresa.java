/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class Empresa
* @date 13/01/2014
*
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpresa")
	private Integer idEmpresa;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "empNombre")
	private String empNombre;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 15)
	@Column(name = "empNit")
	private String empNit;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "empDireccion")
	private String empDireccion;
	@Size(max = 45)
	@Column(name = "empTelefono")
	private String empTelefono;
	@Size(max = 45)
	@Column(name = "empContacto")
	private String empContacto;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "empAlias")
	private String empAlias;

	public Empresa() {
	}

	public Empresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Empresa(Integer idEmpresa, String empNombre, String empNit,
			String empDireccion, String empAlias) {
		this.idEmpresa = idEmpresa;
		this.empNombre = empNombre;
		this.empNit = empNit;
		this.empDireccion = empDireccion;
		this.empAlias = empAlias;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getEmpNombre() {
		return empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}

	public String getEmpNit() {
		return empNit;
	}

	public void setEmpNit(String empNit) {
		this.empNit = empNit;
	}

	public String getEmpDireccion() {
		return empDireccion;
	}

	public void setEmpDireccion(String empDireccion) {
		this.empDireccion = empDireccion;
	}

	public String getEmpTelefono() {
		return empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}

	public String getEmpContacto() {
		return empContacto;
	}

	public void setEmpContacto(String empContacto) {
		this.empContacto = empContacto;
	}

	public String getEmpAlias() {
		return empAlias;
	}

	public void setEmpAlias(String empAlias) {
		this.empAlias = empAlias;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Empresa)) {
			return false;
		}
		Empresa other = (Empresa) object;
		if ((this.idEmpresa == null && other.idEmpresa != null)
				|| (this.idEmpresa != null && !this.idEmpresa
						.equals(other.idEmpresa))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return empNombre + " [ " + empNit + " ]";
	}

}
