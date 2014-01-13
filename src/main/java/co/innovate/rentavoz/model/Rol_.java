package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.319-0500")
@StaticMetamodel(Rol.class)
public class Rol_ {
	public static volatile SingularAttribute<Rol, Integer> idRol;
	public static volatile SingularAttribute<Rol, String> rolNombre;
	public static volatile SingularAttribute<Rol, String> rolDescripcion;
	public static volatile ListAttribute<Rol, Roles> rolesList;
}
