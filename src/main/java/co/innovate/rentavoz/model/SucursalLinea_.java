package co.innovate.rentavoz.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Linea;

@Generated(value="Dali", date="2014-01-12T23:03:31.321-0500")
@StaticMetamodel(SucursalLinea.class)
public class SucursalLinea_ {
	public static volatile SingularAttribute<SucursalLinea, Integer> idSucursalLinea;
	public static volatile SingularAttribute<SucursalLinea, Date> fecha;
	public static volatile SingularAttribute<SucursalLinea, Integer> sucLinEstado;
	public static volatile SingularAttribute<SucursalLinea, String> sucLinObservacion;
	public static volatile SingularAttribute<SucursalLinea, Sucursal> sucursalidSucursal;
	public static volatile SingularAttribute<SucursalLinea, Linea> lineaidLinea;
}
