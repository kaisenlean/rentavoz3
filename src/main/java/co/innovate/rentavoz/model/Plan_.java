package co.innovate.rentavoz.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.317-0500")
@StaticMetamodel(Plan.class)
public class Plan_ {
	public static volatile SingularAttribute<Plan, Integer> idPlan;
	public static volatile SingularAttribute<Plan, String> plaNombre;
	public static volatile SingularAttribute<Plan, Integer> plaCantidadMinutos;
	public static volatile SingularAttribute<Plan, Integer> plaCostoMinuto;
	public static volatile SingularAttribute<Plan, Integer> plaCostoMin;
	public static volatile SingularAttribute<Plan, Integer> plaCostoMax;
	public static volatile SingularAttribute<Plan, Date> plaFechaVenc;
	public static volatile SingularAttribute<Plan, Date> fecha;
	public static volatile ListAttribute<Plan, Convenio> convenioList;
	public static volatile ListAttribute<Plan, PlanLinea> planLineaList;
	public static volatile SingularAttribute<Plan, Tercero> terceroidTecero;
	public static volatile SingularAttribute<Plan, Operador> operadoridOperador;
}
