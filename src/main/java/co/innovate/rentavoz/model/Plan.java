/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "plan")

public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPlan")
	private Integer idPlan;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "plaNombre")
	private String plaNombre;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCantidadMinutos")
	private int plaCantidadMinutos;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMinuto")
	private int plaCostoMinuto;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMin")
	private int plaCostoMin;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMax")
	private int plaCostoMax;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaFechaVenc")
	@Temporal(TemporalType.DATE)
	private Date plaFechaVenc;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne(optional = false)
	private Tercero terceroidTecero;
	@JoinColumn(name = "Operador_idOperador", referencedColumnName = "idOperador")
	@ManyToOne(optional = false)
	private Operador operadoridOperador;

	
	@Column(name="valor_plan")
	private Double valorPlan;
	
	
	public Plan() {
	}

	public Plan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public Plan(Integer idPlan, String plaNombre, int plaCantidadMinutos,
			int plaCostoMinuto, int plaCostoMin, int plaCostoMax,
			Date plaFechaVenc, Date fecha) {
		this.idPlan = idPlan;
		this.plaNombre = plaNombre;
		this.plaCantidadMinutos = plaCantidadMinutos;
		this.plaCostoMinuto = plaCostoMinuto;
		this.plaCostoMin = plaCostoMin;
		this.plaCostoMax = plaCostoMax;
		this.plaFechaVenc = plaFechaVenc;
		this.fecha = fecha;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getPlaNombre() {
		return plaNombre;
	}

	public void setPlaNombre(String plaNombre) {
		this.plaNombre = plaNombre;
	}

	public int getPlaCantidadMinutos() {
		return plaCantidadMinutos;
	}

	public void setPlaCantidadMinutos(int plaCantidadMinutos) {
		this.plaCantidadMinutos = plaCantidadMinutos;
	}

	public int getPlaCostoMinuto() {
		return plaCostoMinuto;
	}

	public void setPlaCostoMinuto(int plaCostoMinuto) {
		this.plaCostoMinuto = plaCostoMinuto;
	}

	public int getPlaCostoMin() {
		return plaCostoMin;
	}

	public void setPlaCostoMin(int plaCostoMin) {
		this.plaCostoMin = plaCostoMin;
	}

	public int getPlaCostoMax() {
		return plaCostoMax;
	}

	public void setPlaCostoMax(int plaCostoMax) {
		this.plaCostoMax = plaCostoMax;
	}

	public Date getPlaFechaVenc() {
		return plaFechaVenc;
	}

	public void setPlaFechaVenc(Date plaFechaVenc) {
		this.plaFechaVenc = plaFechaVenc;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @return the valorPlan
	 */
	public Double getValorPlan() {
		return valorPlan;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 27/01/2014
	 * @param valorPlan the valorPlan to set
	 */
	public void setValorPlan(Double valorPlan) {
		this.valorPlan = valorPlan;
	}
	
	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
	}

	public Operador getOperadoridOperador() {
		return operadoridOperador;
	}

	public void setOperadoridOperador(Operador operadoridOperador) {
		this.operadoridOperador = operadoridOperador;
	}

	public String getVencimiento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(plaFechaVenc)
				.toString();

	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPlan != null ? idPlan.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Plan)) {
			return false;
		}
		Plan other = (Plan) object;
		if ((this.idPlan == null && other.idPlan != null)
				|| (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return plaNombre;
	}

}
