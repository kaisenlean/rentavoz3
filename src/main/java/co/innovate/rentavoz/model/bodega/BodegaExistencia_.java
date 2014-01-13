package co.innovate.rentavoz.model.bodega;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Sucursal;

@Generated(value="Dali", date="2014-01-12T23:03:31.304-0500")
@StaticMetamodel(BodegaExistencia.class)
public class BodegaExistencia_ {
	public static volatile SingularAttribute<BodegaExistencia, Integer> idEXistencia;
	public static volatile SingularAttribute<BodegaExistencia, String> barCode;
	public static volatile SingularAttribute<BodegaExistencia, String> barCode2;
	public static volatile SingularAttribute<BodegaExistencia, String> barCode3;
	public static volatile SingularAttribute<BodegaExistencia, BodegaItem> bodegaItemBean;
	public static volatile SingularAttribute<BodegaExistencia, BodegaIngreso> bodegaIngreso;
	public static volatile ListAttribute<BodegaExistencia, BodegaSalidaReferencia> bodegaSalidaReferencias;
	public static volatile SingularAttribute<BodegaExistencia, EstadoExistenciaEnum> estado;
	public static volatile SingularAttribute<BodegaExistencia, Sucursal> sucursal;
	public static volatile SingularAttribute<BodegaExistencia, String> color;
	public static volatile SingularAttribute<BodegaExistencia, Double> precioVenta;
	public static volatile SingularAttribute<BodegaExistencia, Double> precioVentaMayoristas;
	public static volatile SingularAttribute<BodegaExistencia, Double> precioCompra;
}
