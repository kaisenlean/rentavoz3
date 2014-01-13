package co.innovate.rentavoz.model.tarea;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import co.innovate.rentavoz.model.Centrope;

@Generated(value="Dali", date="2014-01-12T23:03:31.323-0500")
@StaticMetamodel(Tarea.class)
public class Tarea_ {
	public static volatile SingularAttribute<Tarea, Integer> idTarea;
	public static volatile SingularAttribute<Tarea, String> creador;
	public static volatile SingularAttribute<Tarea, String> descripcion;
	public static volatile SingularAttribute<Tarea, EstadoTareaEnum> estado;
	public static volatile SingularAttribute<Tarea, Date> fechaCreacion;
	public static volatile SingularAttribute<Tarea, String> nombre;
	public static volatile SingularAttribute<Tarea, Centrope> owner;
	public static volatile SingularAttribute<Tarea, String> codTarea;
	public static volatile SingularAttribute<Tarea, String> goDir;
}
