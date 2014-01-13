package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.profile.Usuario;

@Generated(value="Dali", date="2014-01-12T23:03:31.324-0500")
@StaticMetamodel(Tercero.class)
public class Tercero_ {
	public static volatile SingularAttribute<Tercero, Integer> idTecero;
	public static volatile SingularAttribute<Tercero, String> terNombre;
	public static volatile SingularAttribute<Tercero, String> terApellidos;
	public static volatile SingularAttribute<Tercero, String> terTelefono;
	public static volatile SingularAttribute<Tercero, String> terDireccion;
	public static volatile SingularAttribute<Tercero, String> terDocumento;
	public static volatile SingularAttribute<Tercero, TipoTerceroEnum> tipo;
	public static volatile ListAttribute<Tercero, TerceroVenta> terceroVentaList;
	public static volatile ListAttribute<Tercero, Roles> rolesList;
	public static volatile ListAttribute<Tercero, SucursalTercero> sucursalTerceroList;
	public static volatile ListAttribute<Tercero, Plan> planList;
	public static volatile SingularAttribute<Tercero, Usuario> usuario;
	public static volatile SingularAttribute<Tercero, Centrope> centrope;
}
