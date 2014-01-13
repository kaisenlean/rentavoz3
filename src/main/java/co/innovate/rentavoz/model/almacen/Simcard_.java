package co.innovate.rentavoz.model.almacen;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.SucursalSimcard;

@Generated(value="Dali", date="2014-01-12T23:03:31.299-0500")
@StaticMetamodel(Simcard.class)
public class Simcard_ {
	public static volatile SingularAttribute<Simcard, Integer> idSimcard;
	public static volatile SingularAttribute<Simcard, String> simIccid;
	public static volatile SingularAttribute<Simcard, EstadosSimcardEnum> simEstado;
	public static volatile SingularAttribute<Simcard, Date> fecha;
	public static volatile ListAttribute<Simcard, SucursalSimcard> sucursalSimcardList;
	public static volatile ListAttribute<Simcard, Linea> lineas;
	public static volatile SingularAttribute<Simcard, Sucursal> sucursal;
}
