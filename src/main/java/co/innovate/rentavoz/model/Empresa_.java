package co.innovate.rentavoz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.almacen.Linea;

@Generated(value="Dali", date="2014-01-12T23:03:31.312-0500")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, Integer> idEmpresa;
	public static volatile SingularAttribute<Empresa, String> empNombre;
	public static volatile SingularAttribute<Empresa, String> empNit;
	public static volatile SingularAttribute<Empresa, String> empDireccion;
	public static volatile SingularAttribute<Empresa, String> empTelefono;
	public static volatile SingularAttribute<Empresa, String> empContacto;
	public static volatile SingularAttribute<Empresa, String> empAlias;
	public static volatile ListAttribute<Empresa, Convenio> convenioList;
	public static volatile ListAttribute<Empresa, Linea> lineaList;
}
