package co.innovate.rentavoz.views.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.PostLoad;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.views.BaseBean;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class PrinterBean
 * @date 14/08/2013
 * 
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class PrinterBean extends BaseBean {

	@PostConstruct
	public void init() {
		try {
			 ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
		     
			    String serverRealPath = servletContext.getRealPath("/");
			    
			     
			    System.out.println(serverRealPath);
			Opcion opcion = opcionService
					.findByClave(OpcionConstants.PATH_REPORTES);
			if (opcion == null) {
				opcion = new Opcion();
				opcion.setCodigo(OpcionConstants.PATH_REPORTES);
			
				opcion.setValor(serverRealPath);
				opcion.setFija(true);
				opcion.setEstado(true);
				opcionService.save(opcion);
			}else{
				opcion.setValor(serverRealPath);
				opcionService.save(opcion);
			}
			
		} catch (Exception e) {
		
		}
	}

	@ManagedProperty(value = "#{opcionService}")
	private OpcionService opcionService;
	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REPORT_PATH
	 */
	private static final String REPORT_PATH = "/reportes/";
	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         JASPER_EXTENSION
	 */
	private static final String JASPER_EXTENSION = ".jasper";
	protected JasperPrint jasperPrint;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/08/2013
	 * @param reportName
	 * @throws JRException
	 */
	public void init(String reportName, HashMap<String, Object> parameters,
			List<?> secondDatasource) throws JRException {

		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath(REPORT_PATH + reportName + JASPER_EXTENSION);
		try {

			if (secondDatasource == null) {
				jasperPrint = JasperFillManager.fillReport(reportPath,
						parameters, establishConnection());
				// JasperPrintManager.printReport(jasperPrint, false);
				return;
			}
			JRBeanCollectionDataSource dts = new JRBeanCollectionDataSource(
					secondDatasource);
			jasperPrint = JasperFillManager.fillReport(reportPath, parameters,
					dts);
			// JasperPrintManager.printReport(jasperPrint, false);

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportPdf(String reportName, String outputName,
			HashMap<String, Object> parameters) {
		try {
			init(reportName, parameters, null);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".pdf");

			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportPdf(String reportName, String outputName,
			HashMap<String, Object> parameters, List<?> secondDatasource) {
		try {
			init(reportName, parameters, secondDatasource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".pdf");

			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportPdf(String reportName, String outputName) {
		try {
			init(reportName, null, null);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".pdf");

			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportXls(String reportName, String outputName,
			HashMap<String, Object> parameters) {
		try {
			init(reportName, parameters, null);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".xls");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					servletOutputStream);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportXls(String reportName, String outputName) {
		try {
			init(reportName, null, null);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".xls");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					servletOutputStream);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/08/2013
	 * @param reportName
	 * @param outputName
	 * @param outputExtension
	 */
	public void exportXls(String reportName, String outputName,
			HashMap<String, Object> parameters, List<?> secondDatasource) {
		try {
			init(reportName, parameters, secondDatasource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".xls");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					servletOutputStream);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/08/2013
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Connection establishConnection() throws ClassNotFoundException {
		Connection connection = null;
		try {

			Class.forName(opcionService.findByClave(OpcionConstants.DB_DRIVER)
					.getValor());
			StringBuffer oracleURL = new StringBuffer("jdbc:mysql://");
			oracleURL.append(opcionService.findByClave(
					OpcionConstants.DB_IP_PORT).getValor());
			oracleURL.append("/");
			oracleURL.append(opcionService.findByClave(
					OpcionConstants.DB_DATABASE_NAME).getValor());
			connection = DriverManager.getConnection(oracleURL.toString(),
					opcionService.findByClave(OpcionConstants.DB_USER)
							.getValor(),
					opcionService.findByClave(OpcionConstants.DB_PASSWORD)
							.getValor());
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			mensajeError(exception.toString());
		}
		return connection;

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param opcionService
	 *            the opcionService to set
	 */
	public void setOpcionService(OpcionService opcionService) {
		this.opcionService = opcionService;
	}
}