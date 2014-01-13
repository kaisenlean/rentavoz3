package co.innovate.rentavoz.model.almacen;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.301-0500")
@StaticMetamodel(VentaLinea.class)
public class VentaLinea_ {
	public static volatile SingularAttribute<VentaLinea, Integer> idVentaLinea;
	public static volatile SingularAttribute<VentaLinea, BigDecimal> ventLinPrecio;
	public static volatile SingularAttribute<VentaLinea, Integer> ventLinTipo;
	public static volatile SingularAttribute<VentaLinea, BigDecimal> ventLinDeposito;
	public static volatile SingularAttribute<VentaLinea, Linea> lineaidLinea;
	public static volatile SingularAttribute<VentaLinea, Venta> ventaidVenta;
	public static volatile SingularAttribute<VentaLinea, Double> domicilio;
	public static volatile SingularAttribute<VentaLinea, Date> fechaRenovacion;
	public static volatile SingularAttribute<VentaLinea, Double> descuento;
}
