package co.innovate.rentavoz.model.almacen;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Pago;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TerceroVenta;

@Generated(value="Dali", date="2014-01-12T23:03:31.300-0500")
@StaticMetamodel(Venta.class)
public class Venta_ {
	public static volatile SingularAttribute<Venta, Integer> idVenta;
	public static volatile SingularAttribute<Venta, Date> venFecha;
	public static volatile SingularAttribute<Venta, Date> fecha;
	public static volatile SingularAttribute<Venta, BigDecimal> venDomicilio;
	public static volatile SingularAttribute<Venta, BigDecimal> venSaldo;
	public static volatile ListAttribute<Venta, Pago> pagoList;
	public static volatile ListAttribute<Venta, TerceroVenta> terceroVentaList;
	public static volatile ListAttribute<Venta, VentaLinea> ventaLineaList;
	public static volatile SingularAttribute<Venta, String> observacion;
	public static volatile SingularAttribute<Venta, ModalidaVentaEnum> modalidadVenta;
	public static volatile SingularAttribute<Venta, EstadoVentaEnum> estadoVenta;
	public static volatile SingularAttribute<Venta, BigDecimal> descuento;
	public static volatile SingularAttribute<Venta, Date> fechaRenovacion;
	public static volatile ListAttribute<Venta, Cuota> cuotas;
	public static volatile SingularAttribute<Venta, Tercero> tercero;
}
