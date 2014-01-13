package co.innovate.rentavoz.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.313-0500")
@StaticMetamodel(Gasto.class)
public class Gasto_ {
	public static volatile SingularAttribute<Gasto, Integer> idGasto;
	public static volatile SingularAttribute<Gasto, BigDecimal> gasValor;
	public static volatile SingularAttribute<Gasto, Date> gasFecha;
	public static volatile SingularAttribute<Gasto, Date> fecha;
	public static volatile SingularAttribute<Gasto, String> gasObservacion;
	public static volatile SingularAttribute<Gasto, TipoGasto> tipoGastoidTipoGasto;
	public static volatile SingularAttribute<Gasto, Cuentas> cuentasidCuentas;
}
