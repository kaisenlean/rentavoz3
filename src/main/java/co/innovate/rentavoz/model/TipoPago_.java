package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.326-0500")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ {
	public static volatile SingularAttribute<TipoPago, Integer> idTipoPago;
	public static volatile SingularAttribute<TipoPago, String> tPagoNombre;
	public static volatile SingularAttribute<TipoPago, String> tPagoDescripcion;
	public static volatile ListAttribute<TipoPago, Pago> pagoList;
}
