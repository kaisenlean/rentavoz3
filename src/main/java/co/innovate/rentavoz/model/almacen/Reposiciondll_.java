package co.innovate.rentavoz.model.almacen;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-12T23:03:31.299-0500")
@StaticMetamodel(Reposiciondll.class)
public class Reposiciondll_ {
	public static volatile SingularAttribute<Reposiciondll, Integer> id;
	public static volatile SingularAttribute<Reposiciondll, MotivoRepoEnum> motivoRepo;
	public static volatile SingularAttribute<Reposiciondll, String> observacion;
	public static volatile SingularAttribute<Reposiciondll, Venta> venta;
}
