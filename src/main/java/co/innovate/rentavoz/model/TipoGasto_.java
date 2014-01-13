package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.325-0500")
@StaticMetamodel(TipoGasto.class)
public class TipoGasto_ {
	public static volatile SingularAttribute<TipoGasto, Integer> idTipoGasto;
	public static volatile SingularAttribute<TipoGasto, String> tGastoNombre;
	public static volatile SingularAttribute<TipoGasto, String> tGastoDescripcion;
	public static volatile ListAttribute<TipoGasto, Gasto> gastoList;
}
