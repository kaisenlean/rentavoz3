package co.innovate.rentavoz.model.bodega;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.306-0500")
@StaticMetamodel(BodegaItem.class)
public class BodegaItem_ {
	public static volatile SingularAttribute<BodegaItem, Integer> id;
	public static volatile SingularAttribute<BodegaItem, String> descripcion;
	public static volatile SingularAttribute<BodegaItem, String> foto;
	public static volatile SingularAttribute<BodegaItem, String> nombre;
	public static volatile SingularAttribute<BodegaItem, String> referencia;
	public static volatile SingularAttribute<BodegaItem, Integer> diasGarantia;
	public static volatile SingularAttribute<BodegaItem, Integer> cantidadImei;
	public static volatile ListAttribute<BodegaItem, BodegaExistencia> bodegaExistencias;
}
