package co.innovate.rentavoz.model.caja;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.profile.Usuario;

@Generated(value="Dali", date="2014-01-12T23:03:31.308-0500")
@StaticMetamodel(Caja.class)
public class Caja_ {
	public static volatile SingularAttribute<Caja, Integer> id;
	public static volatile SingularAttribute<Caja, EstadoCaja> estado;
	public static volatile SingularAttribute<Caja, Date> fecha;
	public static volatile SingularAttribute<Caja, Usuario> usuario;
}
