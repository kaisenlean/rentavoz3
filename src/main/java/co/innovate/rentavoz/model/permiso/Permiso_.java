package co.innovate.rentavoz.model.permiso;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Menu;

@Generated(value="Dali", date="2014-01-12T23:03:31.316-0500")
@StaticMetamodel(Permiso.class)
public class Permiso_ {
	public static volatile SingularAttribute<Permiso, Integer> id;
	public static volatile SingularAttribute<Permiso, Boolean> acceso;
	public static volatile SingularAttribute<Permiso, Menu> menu;
}
