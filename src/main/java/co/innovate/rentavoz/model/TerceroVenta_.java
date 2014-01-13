package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Venta;

@Generated(value="Dali", date="2014-01-12T23:03:31.324-0500")
@StaticMetamodel(TerceroVenta.class)
public class TerceroVenta_ {
	public static volatile SingularAttribute<TerceroVenta, Integer> idTerVen;
	public static volatile SingularAttribute<TerceroVenta, Integer> terVenTipo;
	public static volatile SingularAttribute<TerceroVenta, Venta> ventaidVenta;
	public static volatile SingularAttribute<TerceroVenta, Tercero> terceroidTecero;
}
