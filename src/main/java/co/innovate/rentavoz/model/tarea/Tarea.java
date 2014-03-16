package co.innovate.rentavoz.model.tarea;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;

/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTarea;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "creador")
	private String creador;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "descripcion")
	private String descripcion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Enumerated(EnumType.STRING)
	private EstadoTareaEnum estado;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * co.com.rentavoz.logica.jpa.entidades co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "owner", referencedColumnName = "id")
	private Centrope owner;

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 */
	
	
	@Column(name="codTarea")
	private String codTarea;
	
	
	
	
	@Column(name = "goURL")
	private String goDir;
	
	
	@Column(name="fecha_finaliza")
	@Temporal(TemporalType.DATE)
	private Date fechaFinaliza;
	
	
	
	@JoinColumn(name="usuario_finaliza")
	@ManyToOne
	private Tercero usuarioFinaliza;
	
	
	@Column(name="tipo_tarea")
	@Enumerated(EnumType.STRING)
	private TipoTareaEnum tipoTarea;
	
	public Tarea() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public int getIdTarea() {
		return this.idTarea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param idTarea
	 */
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public String getCreador() {
		return this.creador;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param creador
	 */
	public void setCreador(String creador) {
		this.creador = creador;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the estado
	 */
	public EstadoTareaEnum getEstado() {
		return estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(EstadoTareaEnum estado) {
		this.estado = estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the owner
	 */
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return
	 */
	public Centrope getOwner() {
		return owner;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param owner
	 *            the owner to set
	 */
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param owner
	 */
	public void setOwner(Centrope owner) {
		this.owner = owner;
	}
	
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the codTarea
	 */
	public String getCodTarea() {
		return codTarea;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param codTarea the codTarea to set
	 */
	public void setCodTarea(String codTarea) {
		this.codTarea = codTarea;
	}
	
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the goDir
	 */
	public String getGoDir() {
		return goDir;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param goDir the goDir to set
	 */
	public void setGoDir(String goDir) {
		this.goDir = goDir;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the fechaFinaliza
	 */
	public Date getFechaFinaliza() {
		return fechaFinaliza;
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @return the usuarioFinaliza
	 */
	public Tercero getUsuarioFinaliza() {
		return usuarioFinaliza;
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param fechaFinaliza the fechaFinaliza to set
	 */
	public void setFechaFinaliza(Date fechaFinaliza) {
		this.fechaFinaliza = fechaFinaliza;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/03/2014
	 * @param usuarioFinaliza the usuarioFinaliza to set
	 */
	public void setUsuarioFinaliza(Tercero usuarioFinaliza) {
		this.usuarioFinaliza = usuarioFinaliza;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @return the tipoTarea
	 */
	public TipoTareaEnum getTipoTarea() {
		return tipoTarea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 16/03/2014
	 * @param tipoTarea the tipoTarea to set
	 */
	public void setTipoTarea(TipoTareaEnum tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

}