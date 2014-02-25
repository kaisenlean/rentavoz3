/**
 * 
 */
package co.innovate.rentavoz.services.notificacionlinea.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.mail.MailService;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.almacen.linea.consumo.LineaConsumoService;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;
import co.innovate.rentavoz.services.notificacionlinea.EnvioLineaConsumoService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoServiceImpl
 * @date 25/02/2014
 * 
 */
@Service("envioLineaConsumoService")
public class EnvioLineaConsumoServiceImpl implements EnvioLineaConsumoService {

	/**
	 * 27/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         IMAGEN_PARAM
	 */
	private static final String IMAGEN_PARAM = "IMAGEN";

	/**
	 * 27/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         NAME_FILE
	 */
	private static final String NAME_FILE = "lineas_repo_consumos_";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         XLS_EXTENSION
	 */
	private static final String XLS_EXTENSION = ".xls";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         FORMATO_FECHA
	 */
	private static final String FORMATO_FECHA = "dd-MM-yyyy";



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
	private static final String REPORT_NAME = "lineas_consumo";



	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         USER_DIR
	 */

	@Autowired
	private OpcionService opcionService;

	@Autowired
	private MailService mailService;

	@Autowired
	private CentropeService centropeService;

	@Autowired
	private TerceroService terceroService;
	
	@Autowired
	private FechaFacturacionService fechaFacturacionService;

	private Logger logger = Logger
			.getLogger(EnvioLineaConsumoServiceImpl.class);

	@Autowired
	private LineaConsumoService lineaConsumoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.services.notificacionlinea.EnvioLineaConsumoService
	 * #enviarNotificacionLineasConsumoNoVendidas()
	 */
	@Override
	public void enviarNotificacionLineasConsumoNoVendidas()
			throws BaseException {
		List<Linea> lineas = new ArrayList<Linea>();
		Calendar calendar = Calendar.getInstance();
		FechaFacturacion periodo = fechaFacturacionService.findByFecha(calendar.getTime());
		for (int corte = calendar.getMinimum(Calendar.DAY_OF_MONTH); corte < calendar.getMaximum(Calendar.DAY_OF_MONTH)+1; corte++) {
			logger.info("====== Consultando información del dia ".concat(String.valueOf(corte)).concat("======="));
			lineas.addAll(lineaConsumoService.findLineasNoVendidasConConsumo(periodo, corte));
			logger.info("===Terminado====");
			
		}
	try {
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put(IMAGEN_PARAM,"/"+ opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA).getValor());
			
			
			generarReporte(mapa,lineas);
			
			String users=extraxtMailUsers();
			
			mailService.send(
					users,
					"[ Sistema Rentavoz ] - Informe de lineas no vendidas con consumos ",
					"Buen día , ya está disponible su informe de las lineas no vendidas que tienen consumos ",
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
	private void generarReporte(HashMap<String, Object> mapa,List<Linea> lineas) {
		try { 
			
		StringBuilder reportPath = new StringBuilder(
				opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor()).append("reportes/").append( REPORT_NAME
				+ REPOT_EXTENSION);
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(reportPath.toString(),
				mapa, new JRBeanCollectionDataSource(lineas));

		
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
					if (!tercero.getEmail().equals(StringUtils.EMPTY)) {
						
					builder.append(tercero.getEmail());
					builder.append(";");
					}
				}
				
			}
		}
		return builder.toString();
	}



}
