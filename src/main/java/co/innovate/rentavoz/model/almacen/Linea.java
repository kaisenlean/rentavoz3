/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model.almacen;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;

/**
 * S
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class Linea
 * @date 17/07/2013
 * 
 */
@Entity
@Table(name = "Linea")
public class Linea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idLinea")
	private Integer idLinea;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 11)
	@Column(name = "linNumero")
	private String linNumero;
	@Basic(optional = false)
	@NotNull
	@Column(name = "linCorte")
	private int linCorte;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@JoinColumn(name = "EstadoLinea_idEstadoLinea", referencedColumnName = "idEstadoLinea")
	@ManyToOne(optional = false)
	private EstadoLinea estadoLineaidEstadoLinea;
	@JoinColumn(name = "Empresa_idEmpresa", referencedColumnName = "idEmpresa")
	@ManyToOne(optional = false)
	private Empresa empresaidEmpresa;
	@JoinColumn(name = "plan", referencedColumnName = "idPlan")
	@ManyToOne(optional = true)
	private Plan plan;
	@JoinColumn(name = "simcard", referencedColumnName = "idSimcard")
	@ManyToOne
	private Simcard simcard;

	@JoinColumn(name = "sucursal", referencedColumnName = "idSucursal")
	@ManyToOne
	private Sucursal sucursal;

	
	@Transient
	private boolean seleccionado;

	@Transient
	private double descuento;

	@ManyToOne
	@JoinColumn(name="responsable",referencedColumnName="idTecero")
	private Tercero encargado;
	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 */
	public Linea() {
		plan = new Plan();
		linCorte=BigInteger.ONE.intValue();
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param idLinea
	 */
	public Linea(Integer idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param idLinea
	 * @param linNumero
	 * @param linCorte
	 * @param fecha
	 */
	public Linea(Integer idLinea, String linNumero, int linCorte, Date fecha) {
		this.idLinea = idLinea;
		this.linNumero = linNumero;
		this.linCorte = linCorte;
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linNumero == null) ? 0 : linNumero.hashCode());
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
		Linea other = (Linea) obj;
		if (linNumero == null) {
			if (other.linNumero != null)
				return false;
		} else if (!linNumero.equals(other.linNumero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return linNumero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idLinea
	 */
	public Integer getIdLinea() {
		return idLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idLinea
	 *            the idLinea to set
	 */
	public void setIdLinea(Integer idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the linNumero
	 */
	public String getLinNumero() {
		return linNumero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param linNumero
	 *            the linNumero to set
	 */
	public void setLinNumero(String linNumero) {
		this.linNumero = linNumero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the linCorte
	 */
	public int getLinCorte() {
		return linCorte;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param linCorte
	 *            the linCorte to set
	 */
	public void setLinCorte(int linCorte) {
		this.linCorte = linCorte;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the estadoLineaidEstadoLinea
	 */
	public EstadoLinea getEstadoLineaidEstadoLinea() {
		return estadoLineaidEstadoLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estadoLineaidEstadoLinea
	 *            the estadoLineaidEstadoLinea to set
	 */
	public void setEstadoLineaidEstadoLinea(EstadoLinea estadoLineaidEstadoLinea) {
		this.estadoLineaidEstadoLinea = estadoLineaidEstadoLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the empresaidEmpresa
	 */
	public Empresa getEmpresaidEmpresa() {
		return empresaidEmpresa;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param empresaidEmpresa
	 *            the empresaidEmpresa to set
	 */
	public void setEmpresaidEmpresa(Empresa empresaidEmpresa) {
		this.empresaidEmpresa = empresaidEmpresa;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the simcard
	 */
	public Simcard getSimcard() {
		return simcard;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param simcard
	 *            the simcard to set
	 */
	public void setSimcard(Simcard simcard) {
		this.simcard = simcard;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param descuento
	 *            the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param encargado the encargado to set
	 */
	public void setEncargado(Tercero encargado) {
		this.encargado = encargado;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the encargado
	 */
	public Tercero getEncargado() {
		return encargado;
	}
	
	
}
