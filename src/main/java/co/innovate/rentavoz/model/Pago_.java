package co.innovate.rentavoz.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Venta;

@Generated(value="Dali", date="2014-01-12T23:03:31.315-0500")
@StaticMetamodel(Pago.class)
public class Pago_ {
	public static volatile SingularAttribute<Pago, Integer> idPago;
	public static volatile SingularAttribute<Pago, Date> fecha;
	public static volatile SingularAttribute<Pago, BigDecimal> pagValor;
	public static volatile SingularAttribute<Pago, Date> pagFechaProx;
	public static volatile SingularAttribute<Pago, TipoPago> tipoPagoidTipoPago;
	public static volatile SingularAttribute<Pago, Cuentas> cuentasidCuentas;
	public static volatile SingularAttribute<Pago, Venta> ventaidVenta;
}
