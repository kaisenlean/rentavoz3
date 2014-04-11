/**
 * 
 */
package co.innovate.rentavoz.model.planpago;

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
 * @class PlanPago
 * @date 9/04/2014
 * 
 */
@Entity
@Table(name = "plan_pago")
public class PlanPago implements Serializable {

	/**
	 * 9/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "numero_cuotas")
	private int numeroCuotas;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name="dias_diferencia")
	private int diasDiferencia;

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 */
	public PlanPago() {
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @return the numeroCuotas
	 */
	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @param numeroCuotas
	 *            the numeroCuotas to set
	 */
	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/04/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/04/2014
	 * @return the diasDiferencia
	 */
	public int getDiasDiferencia() {
		return diasDiferencia;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 10/04/2014
	 * @param diasDiferencia the diasDiferencia to set
	 */
	public void setDiasDiferencia(int diasDiferencia) {
		this.diasDiferencia = diasDiferencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

	
	
}