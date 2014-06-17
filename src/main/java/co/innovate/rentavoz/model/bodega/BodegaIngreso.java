package co.innovate.rentavoz.model.bodega;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;


/**
 * The persistent class for the bodega_ingreso database table.
 * 
 */
@Entity
@Table(name="bodega_ingreso")
public class BodegaIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="nro_factura", length=45)
	private String nroFactura;

	@Column(name="persona_compra", length=200)
	private String personaCompra;

	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Tercero proveedor;
	
	@Column(name="consecutivo_factura")
	private String consecutivoFactura;
	

	@Transient
	private List<BodegaExistencia> bodegaExistencias= new ArrayList<BodegaExistencia>();

	@ManyToOne
	@JoinColumn(name="sucursal")
	private Sucursal sucursal;
	
	@Column(name="cantidad_items")
	private int cantidadItems;
	
	/**
	 * 30/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * entrega
	 */
	@Column
	private String entrega;
	
	/**
	 * 30/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * recibe
	 */
	@Column
	private String recibe;
	
	

	@Transient
	private Double precioVenta;
	
	@Transient
	private Double precioVentaMayoristas;
	
	
	@Transient
	private BigDecimal precioCompra;
	

	@Transient
	private BodegaExistenciaColor color;
	
	
	@Transient
	private String subBodega;
	public BodegaIngreso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Time getHoraIngreso() {
		return this.horaIngreso;
	}

	public void setHoraIngreso(Time horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getPersonaCompra() {
		return this.personaCompra;
	}

	public void setPersonaCompra(String personaCompra) {
		this.personaCompra = personaCompra;
	}

	public BigDecimal getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Tercero getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Tercero proveedor) {
		this.proveedor = proveedor;
	}

	public List<BodegaExistencia> getBodegaExistencias() {
		return this.bodegaExistencias;
	}

	public void setBodegaExistencias(List<BodegaExistencia> bodegaExistencias) {
		this.bodegaExistencias = bodegaExistencias;
	}
	
	

	public BodegaExistencia addBodegaExistencia(BodegaExistencia bodegaExistencia) {
		bodegaExistencia.setBodegaIngreso(this);
		getBodegaExistencias().add(bodegaExistencia);
		return bodegaExistencia;
	}

	public BodegaExistencia removeBodegaExistencia(BodegaExistencia bodegaExistencia) {
		getBodegaExistencias().remove(bodegaExistencia);
		bodegaExistencia.setBodegaIngreso(null);
		return bodegaExistencia;
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
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the consecutivoFactura
	 */
	public String getConsecutivoFactura() {
		return consecutivoFactura;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param consecutivoFactura the consecutivoFactura to set
	 */
	public void setConsecutivoFactura(String consecutivoFactura) {
		this.consecutivoFactura = consecutivoFactura;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @return the precioVenta
	 */
	public Double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @param precioVenta the precioVenta to set
	 */
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @return the precioVentaMayoristas
	 */
	public Double getPrecioVentaMayoristas() {
		return precioVentaMayoristas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @param precioVentaMayoristas the precioVentaMayoristas to set
	 */
	public void setPrecioVentaMayoristas(Double precioVentaMayoristas) {
		this.precioVentaMayoristas = precioVentaMayoristas;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @return the color
	 */
	public BodegaExistenciaColor getColor() {
		return color;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/01/2014
	 * @param color the color to set
	 */
	public void setColor(BodegaExistenciaColor color) {
		this.color = color;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 30/01/2014
	 * @return the entrega
	 */
	public String getEntrega() {
		return entrega;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 30/01/2014
	 * @param entrega the entrega to set
	 */
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 30/01/2014
	 * @return the recibe
	 */
	public String getRecibe() {
		return recibe;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 30/01/2014
	 * @param recibe the recibe to set
	 */
	public void setRecibe(String recibe) {
		this.recibe = recibe;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the subBodega
	 */
	public String getSubBodega() {
		return subBodega;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param subBodega the subBodega to set
	 */
	public void setSubBodega(String subBodega) {
		this.subBodega = subBodega;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @return the cantidadItems
	 */
	public int getCantidadItems() {
		return cantidadItems;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/06/2014
	 * @param cantidadItems the cantidadItems to set
	 */
	public void setCantidadItems(int cantidadItems) {
		this.cantidadItems = cantidadItems;
	}


}