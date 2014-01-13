package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.315-0500")
@StaticMetamodel(Operador.class)
public class Operador_ {
	public static volatile SingularAttribute<Operador, Integer> idOperador;
	public static volatile SingularAttribute<Operador, String> opeNombre;
	public static volatile ListAttribute<Operador, Plan> planList;
}
