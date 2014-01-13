package co.innovate.rentavoz.model.almacen;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.294-0500")
@StaticMetamodel(Cuota.class)
public class Cuota_ {
	public static volatile SingularAttribute<Cuota, Integer> numero;
	public static volatile SingularAttribute<Cuota, Venta> venta;
	public static volatile SingularAttribute<Cuota, EstadoCuotaEnum> estadoCuota;
	public static volatile SingularAttribute<Cuota, BigDecimal> valorCuota;
	public static volatile SingularAttribute<Cuota, Date> fechaPago;
}
