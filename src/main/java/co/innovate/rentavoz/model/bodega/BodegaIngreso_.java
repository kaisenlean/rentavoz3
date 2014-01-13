package co.innovate.rentavoz.model.bodega;

import java.sql.Time;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;

@Generated(value="Dali", date="2014-01-12T23:03:31.305-0500")
@StaticMetamodel(BodegaIngreso.class)
public class BodegaIngreso_ {
	public static volatile SingularAttribute<BodegaIngreso, Integer> id;
	public static volatile SingularAttribute<BodegaIngreso, String> descripcion;
	public static volatile SingularAttribute<BodegaIngreso, Date> fechaIngreso;
	public static volatile SingularAttribute<BodegaIngreso, Time> horaIngreso;
	public static volatile SingularAttribute<BodegaIngreso, String> nroFactura;
	public static volatile SingularAttribute<BodegaIngreso, String> personaCompra;
	public static volatile SingularAttribute<BodegaIngreso, Tercero> proveedor;
	public static volatile SingularAttribute<BodegaIngreso, String> consecutivoFactura;
	public static volatile ListAttribute<BodegaIngreso, BodegaExistencia> bodegaExistencias;
	public static volatile SingularAttribute<BodegaIngreso, Sucursal> sucursal;
}
