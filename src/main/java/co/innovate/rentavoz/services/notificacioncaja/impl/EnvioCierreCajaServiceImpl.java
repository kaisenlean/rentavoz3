/**
 * 
 */
package co.innovate.rentavoz.services.notificacioncaja.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.mail.MailService;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.notificacioncaja.EnvioCierreCajaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.reportes.caja.ReporteCajaService;
import co.innovate.rentavoz.services.reportes.caja.dto.ReporteCajaDto;
import co.innovate.rentavoz.services.sucursal.SucursalService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCierreCajaServiceImpl
 * @date 1/03/2014
 *
 */
@Service("envioCierreCajaService")
public class EnvioCierreCajaServiceImpl implements EnvioCierreCajaService{

	/**
	 * 1/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FECHA_CIERRE_PARAM
	 */
	private static final String FECHA_CIERRE_PARAM = "FECHA_CIERRE";

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
	private static final String NAME_FILE = "cierre_caja_";

	/**
	 * 26/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         XLS_EXTENSION
	 */
	private static final String PDF_EXTENSION = ".pdf";

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
	private static final String REPORT_NAME = "cierre_caja";

	@Autowired
	private OpcionService opcionService;

	@Autowired
	private MailService mailService;

	@Autowired
	private CentropeService centropeService;

	@Autowired
	private TerceroService terceroService;
		
	@Autowired
	private ReporteCajaService reporteCajaService;
	
	@Autowired
	private SucursalService sucursalService;
	private Logger logger = Logger
			.getLogger(EnvioCierreCajaServiceImpl.class);
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.notificacioncaja.EnvioCierreCajaService#enviarCierreCajaAdministrativo()
	 */
	@Override
	public void enviarCierreCajaAdministrativo() throws BaseException {
try {
	
	Calendar fecha = Calendar.getInstance();

		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put(IMAGEN_PARAM,"/"+ opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA).getValor());
		mapa.put(FECHA_CIERRE_PARAM, fecha.getTime());
		
		List<ReporteCajaDto> cajas= new ArrayList<ReporteCajaDto>();
		for (Sucursal sucursal : sucursalService.findAll()) {
		cajas.addAll(reporteCajaService.reporteCajasBySucursal(sucursal, fecha.getTime()));
		}
		
		generarReporte(mapa,cajas);
		
		String users=extraxtMailUsers();
		
		mailService.send(
				users,
				"[ Sistema Rentavoz ] - Informe de cierres de caja ",
				"Buen día , ya está disponible su informe de cierres de caja ",
				new File(new StringBuffer(opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor())
						.append(NAME_FILE)
						.append(new SimpleDateFormat(FORMATO_FECHA)
								.format(new Date())).append(PDF_EXTENSION)
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
	private void generarReporte(HashMap<String, Object> mapa,List<ReporteCajaDto> lineas) {
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
								.format(new Date())).append(PDF_EXTENSION)
						.toString()));
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				outputStream);
		
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
		List<Centrope> centropes=centropeService.findByParametro(ParametrosCentrope.ADMINISTRATIVO);
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
