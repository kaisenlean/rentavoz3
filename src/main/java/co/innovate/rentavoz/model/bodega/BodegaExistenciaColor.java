/**
 * 
 */
package co.innovate.rentavoz.model.bodega;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BodegaExistenciaColor
 * @date 19/01/2014
 *
 */
@Entity
@Table(name="bodega_existencia_color")
public class BodegaExistenciaColor implements Serializable {

	/**
	 * 19/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String color;
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 */
	public BodegaExistenciaColor() {
	}


	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 19/01/2014
	* @param id
	* @param color
	*/
	public BodegaExistenciaColor(Integer id, String color) {
		super();
		this.id = id;
		this.color = color;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BodegaExistenciaColor [id=" + id + ", color=" + color + "]";
	}
	
	
	
}
