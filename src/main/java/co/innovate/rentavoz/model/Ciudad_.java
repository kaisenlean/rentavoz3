package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.309-0500")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ {
	public static volatile SingularAttribute<Ciudad, Integer> idCiudad;
	public static volatile SingularAttribute<Ciudad, String> ciuNombre;
	public static volatile ListAttribute<Ciudad, Sucursal> sucursalList;
	public static volatile SingularAttribute<Ciudad, Departamento> departamentoidDepartamento;
}
