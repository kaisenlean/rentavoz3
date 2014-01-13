package co.innovate.rentavoz.model.venta;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.328-0500")
@StaticMetamodel(VentaItemCuota.class)
public class VentaItemCuota_ {
	public static volatile SingularAttribute<VentaItemCuota, Integer> id;
	public static volatile SingularAttribute<VentaItemCuota, EstadoVentaItemCuotaEnum> estado;
	public static volatile SingularAttribute<VentaItemCuota, Date> fechaCierre;
	public static volatile SingularAttribute<VentaItemCuota, Date> fechaPago;
	public static volatile SingularAttribute<VentaItemCuota, VentaItem> idVenta;
	public static volatile SingularAttribute<VentaItemCuota, Double> valor;
}
