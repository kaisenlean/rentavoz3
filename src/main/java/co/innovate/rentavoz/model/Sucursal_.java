package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.320-0500")
@StaticMetamodel(Sucursal.class)
public class Sucursal_ {
	public static volatile SingularAttribute<Sucursal, Integer> idSucursal;
	public static volatile SingularAttribute<Sucursal, String> sucNombre;
	public static volatile SingularAttribute<Sucursal, String> sucDireccion;
	public static volatile SingularAttribute<Sucursal, String> sucTelefono;
	public static volatile SingularAttribute<Sucursal, Integer> sucEstado;
	public static volatile ListAttribute<Sucursal, SucursalLinea> sucursalLineaList;
	public static volatile SingularAttribute<Sucursal, Ciudad> ciudadidCiudad;
	public static volatile ListAttribute<Sucursal, SucursalSimcard> sucursalSimcardList;
	public static volatile ListAttribute<Sucursal, SucursalTercero> sucursalTerceroList;
}
