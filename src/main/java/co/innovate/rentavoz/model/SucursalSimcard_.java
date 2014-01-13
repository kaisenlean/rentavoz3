package co.innovate.rentavoz.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Simcard;

@Generated(value="Dali", date="2014-01-12T23:03:31.322-0500")
@StaticMetamodel(SucursalSimcard.class)
public class SucursalSimcard_ {
	public static volatile SingularAttribute<SucursalSimcard, Integer> idSucSim;
	public static volatile SingularAttribute<SucursalSimcard, Date> fecha;
	public static volatile SingularAttribute<SucursalSimcard, String> sucSimObservacion;
	public static volatile SingularAttribute<SucursalSimcard, Integer> sucSimEstado;
	public static volatile SingularAttribute<SucursalSimcard, Simcard> simcardidSimcard;
	public static volatile SingularAttribute<SucursalSimcard, Sucursal> sucursalidSucursal;
}
