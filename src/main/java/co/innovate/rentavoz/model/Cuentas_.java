package co.innovate.rentavoz.model;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.311-0500")
@StaticMetamodel(Cuentas.class)
public class Cuentas_ {
	public static volatile SingularAttribute<Cuentas, Integer> idCuentas;
	public static volatile SingularAttribute<Cuentas, String> cueNombre;
	public static volatile SingularAttribute<Cuentas, String> cueNumero;
	public static volatile SingularAttribute<Cuentas, BigDecimal> cueSaldo;
	public static volatile ListAttribute<Cuentas, Pago> pagoList;
	public static volatile ListAttribute<Cuentas, Gasto> gastoList;
	public static volatile SingularAttribute<Cuentas, Banco> banco;
}
