package co.innovate.rentavoz.model.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.model.facturacion.Talonario;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class VentaItem
 * @date 29/10/2013
 * 
 */
@Entity
@Table(name = "venta_item")
public class VentaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenta;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Tercero cliente;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double descuento;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private EstadoVentaItemEnum estado;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	private Date fecha;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Lob
	private String observacion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double valorPagar;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Tercero vendedor;

	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne
	@JoinColumn(name="cuenta")
	private Cuentas cuenta;
	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name="modo_pago")
	@Enumerated(EnumType.STRING)
	private ModoPagoEnum modoPago;
	
	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(mappedBy = "idVenta",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<VentaItemDetalleItem> existencias = new ArrayList<VentaItemDetalleItem>();

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(mappedBy = "idVenta" ,cascade=CascadeType.ALL)
	private List<VentaItemCuota> cuotas = new ArrayList<VentaItemCuota>();

	
	
	
	@ManyToOne
	@JoinColumn(name = "cobrador")
	private Tercero cobrador;
	
	
	
	
	@ManyToOne
	@JoinColumn(name="fecha_facturacion")
	private FechaFacturacion fechaFacturacion;
	
	
	
	@Transient
	private Double valorAbono;
	
	@ManyToOne
	@JoinColumn(name="sucursal")
	private Sucursal sucursal;
	
	
	@Column(name="fecha_anulacion")
	@Temporal(TemporalType.DATE)
	private Date fechaAnulacion;
	
	@Lob
	@Column(name="justificacion_anulacion")
	private String justificacionAnulacion;
	
	
	@ManyToOne
	@JoinColumn(name="responsable_anulacion")
	private Tercero responsableAnulacion;
	
	@ManyToOne
	@JoinColumn(name="numero_factura")
	private Talonario numeroFactura;
	
	
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public VentaItem() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Integer getIdVenta() {
		return this.idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param idVenta
	 */
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Tercero getCliente() {
		return this.cliente;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cliente
	 */
	public void setCliente(Tercero cliente) {
		this.cliente = cliente;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public double getDescuento() {
		return this.descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param descuento
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public EstadoVentaItemEnum getEstado() {
		return this.estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param estado
	 */
	public void setEstado(EstadoVentaItemEnum estado) {
		this.estado = estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Date getFecha() {
		return this.fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public String getObservacion() {
		return this.observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public double getValorPagar() {
		return this.valorPagar;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param valorPagar
	 */
	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Tercero getVendedor() {
		return this.vendedor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param vendedor
	 */
	public void setVendedor(Tercero vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the existencias
	 */
	public List<VentaItemDetalleItem> getExistencias() {
		return existencias;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param existencias
	 *            the existencias to set
	 */
	public void setExistencias(List<VentaItemDetalleItem> existencias) {
		this.existencias = existencias;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuotas
	 */
	public List<VentaItemCuota> getCuotas() {
		return cuotas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(List<VentaItemCuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuenta
	 */
	public Cuentas getCuenta() {
		return cuenta;
	} 
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuentas cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the modoPago
	 */
	public ModoPagoEnum getModoPago() {
		return modoPago;
	}

/**
 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 *@date 2/06/2013
 * @param modoPago the modoPago to set
 */
public void setModoPago(ModoPagoEnum modoPago) {
	this.modoPago = modoPago;
}	


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 2/02/2014
 * @param cobrador the cobrador to set
 */
public void setCobrador(Tercero cobrador) {
	this.cobrador = cobrador;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 2/02/2014
 * @return the cobrador
 */
public Tercero getCobrador() {
	return cobrador;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 2/02/2014
 * @return the fechaFacturacion
 */
public FechaFacturacion getFechaFacturacion() {
	return fechaFacturacion;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 2/02/2014
 * @param fechaFacturacion the fechaFacturacion to set
 */
public void setFechaFacturacion(FechaFacturacion fechaFacturacion) {
	this.fechaFacturacion = fechaFacturacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @return the valorAbono
 */
public Double getValorAbono() {
	return valorAbono;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 17/02/2014
 * @param valorAbono the valorAbono to set
 */
public void setValorAbono(Double valorAbono) {
	this.valorAbono = valorAbono;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 27/02/2014
 * @return the sucursal
 */
public Sucursal getSucursal() {
	return sucursal;
}


/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 27/02/2014
 * @param sucursal the sucursal to set
 */
public void setSucursal(Sucursal sucursal) {
	this.sucursal = sucursal;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @return the fechaAnulacion
 */
public Date getFechaAnulacion() {
	return fechaAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @param fechaAnulacion the fechaAnulacion to set
 */
public void setFechaAnulacion(Date fechaAnulacion) {
	this.fechaAnulacion = fechaAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @return the justificacionAnulacion
 */
public String getJustificacionAnulacion() {
	return justificacionAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @param justificacionAnulacion the justificacionAnulacion to set
 */
public void setJustificacionAnulacion(String justificacionAnulacion) {
	this.justificacionAnulacion = justificacionAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @return the responsableAnulacion
 */
public Tercero getResponsableAnulacion() {
	return responsableAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 16/03/2014
 * @param responsableAnulacion the responsableAnulacion to set
 */
public void setResponsableAnulacion(Tercero responsableAnulacion) {
	this.responsableAnulacion = responsableAnulacion;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 21/04/2014
 * @return the numeroFactura
 */
public Talonario getNumeroFactura() {
	return numeroFactura;
}

/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 21/04/2014
 * @param numeroFactura the numeroFactura to set
 */
public void setNumeroFactura(Talonario numeroFactura) {
	this.numeroFactura = numeroFactura;
}

}