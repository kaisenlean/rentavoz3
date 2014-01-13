package co.innovate.rentavoz.model.venta;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.model.Tercero;

@Generated(value="Dali", date="2014-01-12T23:03:31.326-0500")
@StaticMetamodel(VentaItem.class)
public class VentaItem_ {
	public static volatile SingularAttribute<VentaItem, Integer> idVenta;
	public static volatile SingularAttribute<VentaItem, Tercero> cliente;
	public static volatile SingularAttribute<VentaItem, Double> descuento;
	public static volatile SingularAttribute<VentaItem, EstadoVentaItemEnum> estado;
	public static volatile SingularAttribute<VentaItem, Date> fecha;
	public static volatile SingularAttribute<VentaItem, String> observacion;
	public static volatile SingularAttribute<VentaItem, Double> valorPagar;
	public static volatile SingularAttribute<VentaItem, Tercero> vendedor;
	public static volatile SingularAttribute<VentaItem, Cuentas> cuenta;
	public static volatile SingularAttribute<VentaItem, ModoPagoEnum> modoPago;
	public static volatile ListAttribute<VentaItem, VentaItemDetalleItem> existencias;
	public static volatile ListAttribute<VentaItem, VentaItemCuota> cuotas;
}
