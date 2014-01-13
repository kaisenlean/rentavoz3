package co.innovate.rentavoz.model.almacen;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.298-0500")
@StaticMetamodel(LineaConsumo.class)
public class LineaConsumo_ {
	public static volatile SingularAttribute<LineaConsumo, Integer> id;
	public static volatile SingularAttribute<LineaConsumo, Integer> claro;
	public static volatile SingularAttribute<LineaConsumo, Integer> fijo;
	public static volatile SingularAttribute<LineaConsumo, Linea> linea;
	public static volatile SingularAttribute<LineaConsumo, Integer> otros;
	public static volatile SingularAttribute<LineaConsumo, Date> fecha;
}
