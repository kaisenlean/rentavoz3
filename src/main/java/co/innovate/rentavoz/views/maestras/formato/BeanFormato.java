/**
 * 
 */
package co.innovate.rentavoz.views.maestras.formato;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.SortOrder;

import co.innovate.rentavoz.model.formato.Formato;
import co.innovate.rentavoz.model.formato.TipoFormatoEnum;
import co.innovate.rentavoz.services.GenericService;
import co.innovate.rentavoz.services.formato.FormatoService;
import co.innovate.rentavoz.views.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class BeanFormato
 * @date 25/02/2014
 * 
 */
@ManagedBean
@ViewScoped
public class BeanFormato extends StandardAbm<Formato, Integer> implements
		Serializable {

	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{formatoService}")
	private FormatoService formatoService;

	private Logger logger = Logger.getLogger(getClass());

	private String tipoFormato;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		tipoFormato = getObjeto().getTipo().name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		getObjeto().setTipo(TipoFormatoEnum.valueOf(tipoFormato));
		return true;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @param event
	 */
	public void uploadDocumento(FileUploadEvent event) {
		try {

			InputStream inputStream = event.getFile().getInputstream();
			byte[] file = IOUtils.toByteArray(inputStream);
			getObjeto().setArchivo(file);
			getObjeto().setNombreArchivo(event.getFile().getFileName());
		} catch (Exception e) {
			logger.error(e);
			mensajeError(e.toString());
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @param formatoService
	 *            the formatoService to set
	 */
	public void setFormatoService(FormatoService formatoService) {
		this.formatoService = formatoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getFacade()
	 */
	@Override
	public GenericService<Formato, Integer> getFacade() {
		return formatoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getInstancia()
	 */
	@Override
	public Formato getInstancia() {
		return new Formato();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/formato/index.jsf";
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
	public List<Formato> customSearch(int startingAt, int maxPerPage,
			String globalFilter, String sortField, SortOrder sortOrder) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getObjeto()
	 */
	@Override
	public Formato getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.views.StandardAbm#getListado()
	 */
	@Override
	public List<Formato> getListado() {
		return obtenerListado();
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
	 * @date 25/02/2014
	 * @return the tipoFormato
	 */
	public String getTipoFormato() {
		return tipoFormato;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @param tipoFormato
	 *            the tipoFormato to set
	 */
	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

}
