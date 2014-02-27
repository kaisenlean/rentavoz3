/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model.almacen.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.almacen.EstadoVentaEnum;
import co.innovate.rentavoz.model.almacen.ModalidaVentaEnum;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.model.venta.ModoPagoEnum;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class Venta
* @date 7/02/2014
*
 */
@Entity
@Table(name = "venta")
public class Venta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenta")
	private Integer idVenta;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "venFecha")
	@Temporal(TemporalType.DATE)
	private Date venFecha;
	
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Basic
	@Column(name = "venDomicilio")
	private BigDecimal venDomicilio;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "venSaldo")
	private BigDecimal venSaldo;
	
	@Transient
	private List<VentaLinea> ventaLineaList=new ArrayList<VentaLinea>();
	
	@Transient
	private List<Cuota> cuotas=new ArrayList<Cuota>();
	
	
	@Basic(optional = true)
	@Column(name = "observacion")
	private String observacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "modalidad_venta")
	private ModalidaVentaEnum modalidadVenta;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_venta")
	private EstadoVentaEnum estadoVenta;

	@Column(name = "descuento")
	private BigDecimal descuento;

	@Column(name = "fecha_renovacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRenovacion;


	@ManyToOne
	@JoinColumn(name="tercero", referencedColumnName="idTecero")
	private Tercero tercero;
	
	
	@Transient
	private boolean seleccionado;
	
	@ManyToOne
	@JoinColumn(name="fecha_facturacion")
	private FechaFacturacion fechaFacturacion;
	
	@ManyToOne
	@JoinColumn(name="cuenta")
	private Cuentas cuenta;

	
	@ManyToOne
	@JoinColumn(name="cobrador")
	private Tercero cobrador;
	
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Tercero vendedor;
	
	@Column(name="modo_pago")
	@Enumerated(EnumType.STRING)
	private ModoPagoEnum modoPago;
	
	@Transient
	private String valorCuota;
	
	@ManyToOne
	@JoinColumn(name="sucursal")
	private Sucursal sucursal;
	
	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	public Venta() {
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVenta
	 */
	public Venta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVenta
	 * @param venFecha
	 * @param fecha
	 * @param venDomicilio
	 * @param venSaldo
	 */
	public Venta(Integer idVenta, Date venFecha, Date fecha,
			BigDecimal venDomicilio, BigDecimal venSaldo) {
		this.idVenta = idVenta;
		this.venFecha = venFecha;
		this.fecha = fecha;
		this.venDomicilio = venDomicilio;
		this.venSaldo = venSaldo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idVenta != null ? idVenta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Venta)) {
			return false;
		}
		Venta other = (Venta) object;
		if ((this.idVenta == null && other.idVenta != null)
				|| (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Venta[ idVenta=" + idVenta
				+ " ]";
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idVenta
	 */
	public Integer getIdVenta() {
		return idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idVenta
	 *            the idVenta to set
	 */
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venFecha
	 */
	public Date getVenFecha() {
		return venFecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venFecha
	 *            the venFecha to set
	 */
	public void setVenFecha(Date venFecha) {
		this.venFecha = venFecha;
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
	 * @return the venDomicilio
	 */
	public BigDecimal getVenDomicilio() {
		return venDomicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venDomicilio
	 *            the venDomicilio to set
	 */
	public void setVenDomicilio(BigDecimal venDomicilio) {
		this.venDomicilio = venDomicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venSaldo
	 */
	public BigDecimal getVenSaldo() {
		return venSaldo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venSaldo
	 *            the venSaldo to set
	 */
	public void setVenSaldo(BigDecimal venSaldo) {
		this.venSaldo = venSaldo;
	}

	

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventaLineaList
	 */
	public List<VentaLinea> getVentaLineaList() {
		return ventaLineaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventaLineaList
	 *            the ventaLineaList to set
	 */
	public void setVentaLineaList(List<VentaLinea> ventaLineaList) {
		this.ventaLineaList = ventaLineaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the modalidadVenta
	 */
	public ModalidaVentaEnum getModalidadVenta() {
		return modalidadVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param modalidadVenta
	 *            the modalidadVenta to set
	 */
	public void setModalidadVenta(ModalidaVentaEnum modalidadVenta) {
		this.modalidadVenta = modalidadVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fechaRenovacion
	 */
	public Date getFechaRenovacion() {
		return fechaRenovacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fechaRenovacion
	 *            the fechaRenovacion to set
	 */
	public void setFechaRenovacion(Date fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the descuento
	 */
	public BigDecimal getDescuento() {
		return descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param descuento
	 *            the descuento to set
	 */
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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
	 * @return the estadoVenta
	 */
	public EstadoVentaEnum getEstadoVenta() {
		return estadoVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estadoVenta
	 *            the estadoVenta to set
	 */
	public void setEstadoVenta(EstadoVentaEnum estadoVenta) {
		this.estadoVenta = estadoVenta;
	}
	
	public String getModalidadVentaAsString(){
		return modalidadVenta.name();
		
	
	}

	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}
	
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/02/2014
	 * @return the fechaFacturacion
	 */
	public FechaFacturacion getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/02/2014
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(FechaFacturacion fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the cuenta
	 */
	public Cuentas getCuenta() {
		return cuenta;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuentas cuenta) {
		this.cuenta = cuenta;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the cobrador
	 */
	public Tercero getCobrador() {
		return cobrador;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cobrador the cobrador to set
	 */
	public void setCobrador(Tercero cobrador) {
		this.cobrador = cobrador;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the vendedor
	 */
	public Tercero getVendedor() {
		return vendedor;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Tercero vendedor) {
		this.vendedor = vendedor;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @return the cuotas
	 */
	public List<Cuota> getCuotas() {
		return cuotas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/02/2014
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/02/2014
	 * @return the modoPago
	 */
	public ModoPagoEnum getModoPago() {
		return modoPago;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 8/02/2014
	 * @param modoPago the modoPago to set
	 */
	public void setModoPago(ModoPagoEnum modoPago) {
		this.modoPago = modoPago;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @return the valorCuota
	 */
	public String getValorCuota() {
		return valorCuota;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/02/2014
	 * @param valorCuota the valorCuota to set
	 */
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
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
}
