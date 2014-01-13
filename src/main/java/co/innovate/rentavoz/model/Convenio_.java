package co.innovate.rentavoz.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.310-0500")
@StaticMetamodel(Convenio.class)
public class Convenio_ {
	public static volatile SingularAttribute<Convenio, Integer> idConvenio;
	public static volatile SingularAttribute<Convenio, Short> conComision;
	public static volatile SingularAttribute<Convenio, Date> conFechaInicio;
	public static volatile SingularAttribute<Convenio, Short> conValorFijo;
	public static volatile SingularAttribute<Convenio, Integer> conEstado;
	public static volatile SingularAttribute<Convenio, Empresa> empresaidEmpresa;
	public static volatile SingularAttribute<Convenio, Plan> planidPlan;
}
