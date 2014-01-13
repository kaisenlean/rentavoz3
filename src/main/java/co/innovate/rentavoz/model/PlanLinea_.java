package co.innovate.rentavoz.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Linea;

@Generated(value="Dali", date="2014-01-12T23:03:31.318-0500")
@StaticMetamodel(PlanLinea.class)
public class PlanLinea_ {
	public static volatile SingularAttribute<PlanLinea, Integer> idPlanLinea;
	public static volatile SingularAttribute<PlanLinea, Date> fecha;
	public static volatile SingularAttribute<PlanLinea, Integer> plaEstado;
	public static volatile SingularAttribute<PlanLinea, Linea> lineaidLinea;
	public static volatile SingularAttribute<PlanLinea, Plan> planidPlan;
}
