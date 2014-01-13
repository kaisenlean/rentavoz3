package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.311-0500")
@StaticMetamodel(Departamento.class)
public class Departamento_ {
	public static volatile SingularAttribute<Departamento, Integer> idDepartamento;
	public static volatile SingularAttribute<Departamento, String> depNombre;
	public static volatile ListAttribute<Departamento, Ciudad> ciudadList;
}
