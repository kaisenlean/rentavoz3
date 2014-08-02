package co.innovate.rentavoz.model.venta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class VentaItemCuota
 * @date 29/10/2013
 *
 */
@Entity
@Table(name = "venta_item_cuota")
public class VentaItemCuota implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private EstadoVentaItemCuotaEnum estado;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cierre")
	private Date fechaCierre;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago")
	private Date fechaPago;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idVenta")
	private VentaItem idVenta;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double valor;

	@Transient
	private double abono;

	@Transient
	private boolean pagada;

	
	@PostLoad
	public void load(){
		abono=valor;
		
	}
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public VentaItemCuota() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public EstadoVentaItemCuotaEnum getEstado() {
		return this.estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param estado
	 */
	public void setEstado(EstadoVentaItemCuotaEnum estado) {
		this.estado = estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param fechaCierre
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Date getFechaPago() {
		return this.fechaPago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param fechaPago
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public VentaItem getIdVenta() {
		return this.idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param idVenta
	 */
	public void setIdVenta(VentaItem idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public double getValor() {
		return this.valor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the pagada
	 */
	public boolean getPagada() {
		return pagada;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param pagada
	 *            the pagada to set
	 */
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public String getEstadoCuotaAsString() {
		return estado.name();

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/08/2014
	 * @return the abono
	 */
	public double getAbono() {
		return abono;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/08/2014
	 * @param abono
	 *            the abono to set
	 */
	public void setAbono(double abono) {
		this.abono = abono;
	}

}