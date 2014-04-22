/**
 * 
 */
package co.innovate.rentavoz.views.contable.consecutivofactura;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.facturacion.ConsecutivoFactura;
import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.facturacion.ConsecutivoFacturaService;
import co.innovate.rentavoz.services.facturacion.TalonarioService;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.views.StandardAbm;
import co.innovate.rentavoz.views.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsecutivoFacturaBean
 * @date 21/04/2014
 * 
 */
@ManagedBean
@ViewScoped
public class ConsecutivoFacturaBean extends
		StandardAbm<ConsecutivoFactura, Integer> implements Serializable {

	/**
	 * 21/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/contable/consecutivofactura/index.jsf";
	/**
	 * 21/04/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{consecutivoFacturaService}")
	private ConsecutivoFacturaService consecutivoFacturaService;

	@ManagedProperty(value = "#{sucursalService}")
	private SucursalService sucursalService;
	
	@ManagedProperty(value="#{talonarioService}")
	private TalonarioService talonarioService;

	@ManagedProperty(value = "#{login}")
	private Login login;

	private int codSucursal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<ConsecutivoFactura, Integer> getFacade() {
		return consecutivoFacturaService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public ConsecutivoFactura getInstancia() {
		return new ConsecutivoFactura();
	}

	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.views.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
	getObjeto().setActivo(Boolean.TRUE);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		
		codSucursal = getObjeto().getSucursal() == null ? BigInteger.ZERO
				.intValue() : getObjeto().getSucursal().getIdSucursal();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {

		Sucursal sucursal = sucursalService.findById(codSucursal);

		if (sucursal == null) {
			mensajeError("Error al cargar sucursal ;por favor selecciona una sucursal");
			return false;
		}

		if (getObjeto().getId() != null) {
			getObjeto().setUsuarioModifica(login.getTercero());
			getObjeto().setFechaModificacion(Calendar.getInstance().getTime());
		} else {
			getObjeto().setUsuarioCrea(login.getTercero());
			getObjeto().setFechaCreacion(Calendar.getInstance().getTime());
			getObjeto().setUsuarioModifica(login.getTercero());
			getObjeto().setFechaModificacion(Calendar.getInstance().getTime());

		}

		getObjeto().setSucursal(sucursal);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {
		if (!isEdit()) {
			generarTalonario(getObjeto());
			
		}
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param objeto
	 * @return
	 */
	private void generarTalonario(ConsecutivoFactura objeto) {
		for (int i = objeto.getConsecutivo(); i <= getObjeto()
				.getConsecutivoFinal(); i++) {
			Talonario talonario = new Talonario();
			StringBuffer buffer = new StringBuffer(objeto.getLetra());
			buffer.append(String
					.format("%0"
							+ objeto.getConsecutivoFinal().toString().length()
							+ "d", i));
			talonario.setConsecutivo(buffer.toString());
			talonario.setConsecutivoFactura(objeto);
			talonario.setSucursal(objeto.getSucursal());
			talonario.setUsado(Boolean.FALSE);
			talonarioService.save(talonario);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.views.StandardAbm#custoCountBySearch(java.lang.String
	 * )
	 */
	@Override
	public Integer custoCountBySearch(String globalFilter) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#customSearch(int, int,
	 * java.lang.String, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@Override
	public List<ConsecutivoFactura> customSearch(int startingAt,
			int maxPerPage, String globalFilter, String sortField,
			SortOrder sortOrder) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public ConsecutivoFactura getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<ConsecutivoFactura> getListado() {
		return obtenerListado();
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @return the codSucursal
	 */
	public int getCodSucursal() {
		return codSucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param codSucursal
	 *            the codSucursal to set
	 */
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param sucursalService
	 *            the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param consecutivoFacturaService
	 *            the consecutivoFacturaService to set
	 */
	public void setConsecutivoFacturaService(
			ConsecutivoFacturaService consecutivoFacturaService) {
		this.consecutivoFacturaService = consecutivoFacturaService;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/04/2014
	 * @param talonarioService the talonarioService to set
	 */
	public void setTalonarioService(TalonarioService talonarioService) {
		this.talonarioService = talonarioService;
	}
}
