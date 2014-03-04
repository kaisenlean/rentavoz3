/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import co.innovate.rentavoz.model.almacen.Cuota;
import co.innovate.rentavoz.model.profile.Usuario;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class Tercero
* @date 4/09/2013
*
*/
@Entity
@Table(name = "tercero")
public class Tercero implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTecero")
	private Integer idTecero;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "terNombre")
	private String terNombre;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Size(max = 45)
	@Column(name = "terApellidos")
	private String terApellidos;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Basic(optional = false)
	@Column(name = "terTelefono")
	private String terTelefono;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Basic(optional = false)
	@Column(name = "terDireccion")
	private String terDireccion;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "terDocumento")
	private String terDocumento;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTerceroEnum tipo;

	
	
	@Column
	private String email;
	
	@Transient
	private Boolean mayorista;
	
	@Transient
	private List<Cuota> cuotasMora;
	
	
	@Transient
	private Double valorCuotasMora;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
//	private List<TerceroVenta> terceroVentaList;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
//	private List<Roles> rolesList;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Transient
	private List<SucursalTercero> sucursalTerceroList=new ArrayList<SucursalTercero>();
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
//	private List<Plan> planList;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Transient
	private String repTerClave;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name = "usuario", referencedColumnName = "usuar")
	@ManyToOne
	private Usuario usuario;

	
	@ManyToOne
	@JoinColumn(name="centrope" , referencedColumnName="id")
	private Centrope centrope;
	
	
	@ManyToOne
	@JoinColumn(name="ciudad",referencedColumnName="idCiudad")
	private Ciudad ciudad;
	
	
	@Column
	private String barrio;
	
	
	@Transient
	private List<SucursalTercero> eliminados=new ArrayList<SucursalTercero>();
	
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	*/
	public Tercero() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	*/
	public Tercero(Integer idTecero) {
		this.idTecero = idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	* @param terNombre
	* @param terTelefono
	* @param terDireccion
	* @param terDocumento
	*/
	public Tercero(Integer idTecero, String terNombre, String terTelefono,
			String terDireccion, String terDocumento) {
		this.idTecero = idTecero;
		this.terNombre = terNombre;
		this.terTelefono = terTelefono;
		this.terDireccion = terDireccion;
		this.terDocumento = terDocumento;

	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public Integer getIdTecero() {
		return idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	*/
	public void setIdTecero(Integer idTecero) {
		this.idTecero = idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerNombre() {
		return terNombre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terNombre
	*/
	public void setTerNombre(String terNombre) {
		this.terNombre = terNombre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerApellidos() {
		return terApellidos;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terApellidos
	*/
	public void setTerApellidos(String terApellidos) {
		this.terApellidos = terApellidos;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerTelefono() {
		return terTelefono;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terTelefono
	*/
	public void setTerTelefono(String terTelefono) {
		this.terTelefono = terTelefono;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerDireccion() {
		return terDireccion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terDireccion
	*/
	public void setTerDireccion(String terDireccion) {
		this.terDireccion = terDireccion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerDocumento() {
		return terDocumento;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terDocumento
	*/
	public void setTerDocumento(String terDocumento) {
		this.terDocumento = terDocumento;
	}

	



	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
//	@XmlTransient
	public List<SucursalTercero> getSucursalTerceroList() {
		return sucursalTerceroList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param sucursalTerceroList
	*/
	public void setSucursalTerceroList(List<SucursalTercero> sucursalTerceroList) {
		this.sucursalTerceroList = sucursalTerceroList;
	}

	

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTecero != null ? idTecero.hashCode() : 0);
		return hash;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Tercero)) {
			return false;
		}
		Tercero other = (Tercero) object;
		if ((this.idTecero == null && other.idTecero != null)
				|| (this.idTecero != null && !this.idTecero
						.equals(other.idTecero))) {
			return false;
		}
		return true;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getRepTerClave() {
		return repTerClave;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param repTerClave
	*/
	public void setRepTerClave(String repTerClave) {
		this.repTerClave = repTerClave;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tipo
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public TipoTerceroEnum getTipo() {
		return tipo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the usuario
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param usuario
	 *            the usuario to set
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param usuario
	*/
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param tipo
	 *            the tipo to set
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param tipo
	*/
	public void setTipo(TipoTerceroEnum tipo) {
		this.tipo = tipo;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTipoAsString() {
		if (tipo != null) {

			return tipo.name().replace("_", " ");
		} else {
			return "";
		}
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the centrope
	 */
	public Centrope getCentrope() {
		return centrope;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param centrope the centrope to set
	 */
	public void setCentrope(Centrope centrope) {
		this.centrope = centrope;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @return the ciudad
	 */
	public Ciudad getCiudad() {
		return ciudad;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/02/2014
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param mayorista the mayorista to set
	 */
	public void setMayorista(Boolean mayorista) {
		this.mayorista = mayorista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the mayorista
	 */
	public Boolean getMayorista() {
		return mayorista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @return the eliminados
	 */
	public List<SucursalTercero> getEliminados() {
		return eliminados;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param eliminados the eliminados to set
	 */
	public void setEliminados(List<SucursalTercero> eliminados) {
		this.eliminados = eliminados;
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the cuotasMora
	 */
	public List<Cuota> getCuotasMora() {
		return cuotasMora;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param cuotasMora the cuotasMora to set
	 */
	public void setCuotasMora(List<Cuota> cuotasMora) {
		this.cuotasMora = cuotasMora;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @return the valorCuotasMora
	 */
	public Double getValorCuotasMora() {
		return valorCuotasMora;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/03/2014
	 * @param valorCuotasMora the valorCuotasMora to set
	 */
	public void setValorCuotasMora(Double valorCuotasMora) {
		this.valorCuotasMora = valorCuotasMora;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return terNombre + " " + terApellidos + " [ " + terDocumento + " ]";
	}

}
