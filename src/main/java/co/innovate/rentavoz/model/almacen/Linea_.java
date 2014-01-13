package co.innovate.rentavoz.model.almacen;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.PlanLinea;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.SucursalLinea;

@Generated(value="Dali", date="2014-01-12T23:03:31.297-0500")
@StaticMetamodel(Linea.class)
public class Linea_ {
	public static volatile SingularAttribute<Linea, Integer> idLinea;
	public static volatile SingularAttribute<Linea, String> linNumero;
	public static volatile SingularAttribute<Linea, Integer> linCorte;
	public static volatile SingularAttribute<Linea, Date> fecha;
	public static volatile ListAttribute<Linea, SucursalLinea> sucursalLineaList;
	public static volatile ListAttribute<Linea, PlanLinea> planLineaList;
	public static volatile ListAttribute<Linea, VentaLinea> ventaLineaList;
	public static volatile SingularAttribute<Linea, EstadoLinea> estadoLineaidEstadoLinea;
	public static volatile SingularAttribute<Linea, Empresa> empresaidEmpresa;
	public static volatile SingularAttribute<Linea, Plan> plan;
	public static volatile SingularAttribute<Linea, Simcard> simcard;
	public static volatile SingularAttribute<Linea, Sucursal> sucursal;
}
