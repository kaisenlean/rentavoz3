/**
 * 
 */
package co.innovate.rentavoz.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class Opcion
 * @date 17/01/2014
 *
 */
@Entity
@Table(name="opcion")
public class Opcion implements Serializable{

	/**
	 * 17/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;



	@Id
	private String codigo;
	
	@Column
	private String valor;
	
	@Column
	private String descripcion;
	
	@Column
	private Boolean estado;
	
	@Column
	private Boolean fija;

	
	
	
	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 17/01/2014
	* @param codigo
	* @param valor
	* @param descripcion
	* @param estado
	*/
	public Opcion(String codigo, String valor, String descripcion,
			Boolean estado) {
		super();
		this.codigo = codigo;
		this.valor = valor;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 */
	public Opcion() {

	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @return the fija
	 */
	public Boolean getFija() {
		return fija;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 18/01/2014
	 * @param fija the fija to set
	 */
	public void setFija(Boolean fija) {
		this.fija = fija;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Opcion [codigo=" + codigo + "]";
	}
	
	
	
	
}
