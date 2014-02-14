package co.innovate.rentavoz.model.permiso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.innovate.rentavoz.model.Menu;
import co.innovate.rentavoz.model.profile.Usuario;

/**
 * The persistent class for the permiso database table.
 * 
 */
@Entity
@Table(name = "usuario_menu")
public class UsuarioMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private String acceso;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne
	@JoinColumn(name = "menu",referencedColumnName="id")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name="usuario",referencedColumnName="usuar")
	private Usuario usuario;

	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/11/2013
	 */
	public UsuarioMenu() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}



	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param acceso
	 *            the acceso to set
	 */
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the acceso
	 */
	public String getAcceso() {
		return acceso;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}