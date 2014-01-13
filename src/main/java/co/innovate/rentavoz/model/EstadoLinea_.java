package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Linea;

@Generated(value="Dali", date="2014-01-12T23:03:31.313-0500")
@StaticMetamodel(EstadoLinea.class)
public class EstadoLinea_ {
	public static volatile SingularAttribute<EstadoLinea, Integer> idEstadoLinea;
	public static volatile SingularAttribute<EstadoLinea, String> estLinNombre;
	public static volatile ListAttribute<EstadoLinea, Linea> lineaList;
}
