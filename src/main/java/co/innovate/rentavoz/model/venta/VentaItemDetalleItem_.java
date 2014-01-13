package co.innovate.rentavoz.model.venta;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.bodega.BodegaExistencia;

@Generated(value="Dali", date="2014-01-12T23:03:31.328-0500")
@StaticMetamodel(VentaItemDetalleItem.class)
public class VentaItemDetalleItem_ {
	public static volatile SingularAttribute<VentaItemDetalleItem, Integer> id;
	public static volatile SingularAttribute<VentaItemDetalleItem, BodegaExistencia> existencia;
	public static volatile SingularAttribute<VentaItemDetalleItem, VentaItem> idVenta;
}
