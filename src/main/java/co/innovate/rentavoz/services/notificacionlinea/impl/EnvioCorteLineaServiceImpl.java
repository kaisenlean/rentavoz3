/**
 * 
 */
package co.innovate.rentavoz.services.notificacionlinea.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.mail.MailService;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.notificacionlinea.EnvioCorteLineaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCorteLineaServiceImpl
 * @date 26/01/2014
 * 
 */
@Service("envioCorteLineaService")
public class EnvioCorteLineaServiceImpl implements EnvioCorteLineaService {

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * IMAGEN_PARAM
	 */
	private static final String IMAGEN_PARAM = "IMAGEN";

	/**
	 * 27/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * NAME_FILE
	 */
	private static final String NAME_FILE = "lineas_corte_";

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * XLS_EXTENSION
	 */
	private static final String XLS_EXTENSION = ".xls";

	/**
	 * 26/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FORMATO_FECHA
	 */
	private static final String FORMATO_FECHA = "dd-MM-yyyy";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         SLASH
	 */
	private static final String SLASH = "/";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REPOT_EXTENSION
	 */
	private static final String REPOT_EXTENSION = ".jasper";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REPORT_NAME
	 */
	private static final String REPORT_NAME = "lineas_corte";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REPOT_PATH
	 */
	private static final String REPOT_PATH = "/WebContent/reportes/";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         CORTE_PARAM
	 */
	private static final String CORTE_PARAM = "CORTE";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         USER_DIR
	 */
	private static final String USER_DIR = "user.dir";

	@Autowired
	private OpcionService opcionService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private CentropeService centropeService;
	
	@Autowired
	private TerceroService terceroService;

	private Logger logger = Logger.getLogger(EnvioCorteLineaServiceImpl.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.services.notificacionlinea.EnvioCorteLineaService#enviarNotificacionLineasFechaCorte()
	 */
	@Override
	public void enviarNotificacionLineasFechaCorte() {
	
		
		DateFormat format= new SimpleDateFormat("DD");
		String dia=format.format(Calendar.getInstance().getTime());
		int diaInt= Integer.valueOf(dia);
		
		try {
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put(CORTE_PARAM, diaInt);
			mapa.put(IMAGEN_PARAM, opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA).getValor());
			
			
			generarReporte(mapa);
			
			String users=extraxtMailUsers();
			
			mailService.send(
					users,
					"[ Sistema Rentavoz ] - Informe de corte de lineas del dia ",
					"Buen día , ya está disponible su informe de las lineas que tienen fecha de corte programada para el día de hoy",
					new File(new StringBuffer(opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor())
							.append(NAME_FILE)
							.append(new SimpleDateFormat(FORMATO_FECHA)
									.format(new Date())).append(XLS_EXTENSION)
							.toString()));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	* @param mapa
	*/
	private void generarReporte(HashMap<String, Object> mapa) {
		try {
			
		StringBuilder reportPath = new StringBuilder(
				opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor()).append("reportes/").append( REPORT_NAME
				+ REPOT_EXTENSION);
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(reportPath.toString(),
				mapa, establishConnection());

		
		OutputStream outputStream = new FileOutputStream(new File(
				new StringBuffer(opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor())
						.append(NAME_FILE)
						.append(new SimpleDateFormat(FORMATO_FECHA)
								.format(new Date())).append(XLS_EXTENSION)
						.toString()));
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
				jasperPrint);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				outputStream);
		exporterXLS
				.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				true);
		exporterXLS.exportReport();
		} catch (Exception e) {
			logger.error(e);
			
		}
		
		
	}

	/**
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 27/01/2014
	* @return
	*/
	private String extraxtMailUsers() {
		StringBuilder builder= new StringBuilder();
		List<Centrope> centropes=centropeService.findByParametro(ParametrosCentrope.OPERATIVO);
		for (Centrope centrope : centropes) {
			
			List<Tercero> terceros = terceroService.findByCentrope(centrope);
			
			for (Tercero tercero : terceros) {
				if (tercero.getEmail()!=null) {
					
					builder.append(tercero.getEmail());
					builder.append(";");
				}
				
			}
		}
		return builder.toString();
	}

	private Connection establishConnection() throws ClassNotFoundException {
		Connection connection = null;
		try {

			Class.forName(opcionService.findByClave(OpcionConstants.DB_DRIVER)
					.getValor());
			StringBuffer oracleURL = new StringBuffer("jdbc:mysql://");
			oracleURL.append(opcionService.findByClave(
					OpcionConstants.DB_IP_PORT).getValor());
			oracleURL.append(SLASH);
			oracleURL.append(opcionService.findByClave(
					OpcionConstants.DB_DATABASE_NAME).getValor());
			connection = DriverManager.getConnection(oracleURL.toString(),
					opcionService.findByClave(OpcionConstants.DB_USER)
							.getValor(),
					opcionService.findByClave(OpcionConstants.DB_PASSWORD)
							.getValor());
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			logger.error(exception);

		}
		return connection;

	}

}
