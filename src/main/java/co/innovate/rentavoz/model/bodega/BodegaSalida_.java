package co.innovate.rentavoz.model.bodega;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Sucursal;

@Generated(value="Dali", date="2014-01-12T23:03:31.307-0500")
@StaticMetamodel(BodegaSalida.class)
public class BodegaSalida_ {
	public static volatile SingularAttribute<BodegaSalida, Integer> id;
	public static volatile SingularAttribute<BodegaSalida, Date> fechaSalida;
	public static volatile SingularAttribute<BodegaSalida, String> nroFactura;
	public static volatile SingularAttribute<BodegaSalida, String> recibe;
	public static volatile SingularAttribute<BodegaSalida, String> observacion;
	public static volatile SingularAttribute<BodegaSalida, Sucursal> sucursalDestino;
	public static volatile SingularAttribute<BodegaSalida, Sucursal> sucursalOrigen;
	public static volatile ListAttribute<BodegaSalida, BodegaSalidaReferencia> bodegaSalidaReferencias;
}
