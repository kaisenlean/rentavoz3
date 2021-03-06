package co.innovate.rentavoz.model;

import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.innovate.rentavoz.model.permiso.UsuarioMenu;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "label")
	private String label;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "icono")
	private String icono;
	@Size(max = 255)
	@Column(name = "url")
	private String url;
	@Size(max = 100)
	@Column(name = "padre")
	private String padre;

	
	@Column
	private String parametro;
	
	@Transient
	private boolean seleccionado;

	@Transient
	private boolean crea;
	@Transient
	private boolean edita;
	@Transient
	private boolean elimina;
	@Transient
	private boolean imprime;
	
	@Transient
	private UsuarioMenu usuarioMenu;
	public Menu() {
	}

	public Menu(Integer id) {
		this.id = id;
	}

	public Menu(Integer id, String label, String icono) {
		this.id = id;
		this.label = label;
		this.icono = icono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getUrl() {
		return url;
	}

	public String direccion() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the padre
	 */
	public String getPadre() {
		return padre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param padre
	 *            the padre to set
	 */
	public void setPadre(String padre) {
		this.padre = padre;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/01/2014
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Menu)) {
			return false;
		}
		Menu other = (Menu) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return label;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the crea
	 */
	public boolean isCrea() {
		return crea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param crea the crea to set
	 */
	public void setCrea(boolean crea) {
		this.crea = crea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the edita
	 */
	public boolean isEdita() {
		return edita;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param edita the edita to set
	 */
	public void setEdita(boolean edita) {
		this.edita = edita;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the elimina
	 */
	public boolean isElimina() {
		return elimina;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param elimina the elimina to set
	 */
	public void setElimina(boolean elimina) {
		this.elimina = elimina;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the imprime
	 */
	public boolean isImprime() {
		return imprime;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param imprime the imprime to set
	 */
	public void setImprime(boolean imprime) {
		this.imprime = imprime;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @param usuarioMenu the usuarioMenu to set
	 */
	public void setUsuarioMenu(UsuarioMenu usuarioMenu) {
		this.usuarioMenu = usuarioMenu;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 31/01/2014
	 * @return the usuarioMenu
	 */
	public UsuarioMenu getUsuarioMenu() {
		return usuarioMenu;
	}
	
	public void cambioValor(ValueChangeEvent event){
		crea=(Boolean) event.getNewValue();
		
	}
}
