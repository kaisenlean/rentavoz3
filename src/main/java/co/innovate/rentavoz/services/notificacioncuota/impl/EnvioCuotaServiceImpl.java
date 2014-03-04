/**
 * 
 */
package co.innovate.rentavoz.services.notificacioncuota.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import co.innovate.rentavoz.mail.MailService;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.services.almacen.CuotaService;
import co.innovate.rentavoz.services.centrope.CentropeService;
import co.innovate.rentavoz.services.notificacioncuota.EnvioCuotaService;
import co.innovate.rentavoz.services.opcion.OpcionService;
import co.innovate.rentavoz.services.tercero.TerceroService;
import co.innovate.rentavoz.views.session.OpcionConstants;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCuotaServiceImpl
 * @date 4/03/2014
 *
 */
@Service("envioCuotaService")
public class EnvioCuotaServiceImpl implements EnvioCuotaService,Serializable {

	/**
	 * 4/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	

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
	private static final String NAME_FILE = "deudores_morosos_";

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
	private static final String REPORT_NAME = "deudores_morosos";

	@Autowired
	private OpcionService opcionService;

	@Autowired
	private MailService mailService;

	@Autowired
	private CentropeService centropeService;

	@Autowired
	private TerceroService terceroService;
		
	
	@Autowired
	private CuotaService cuotaService;
	
	private Logger logger= Logger.getLogger(EnvioCuotaServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.services.notificacioncuota.EnvioCuotaService#enviarDeudoresMorososHastaLaFecha(java.util.Date)
	 */
	@Override
	public void enviarDeudoresMorososHastaLaFecha(Date date) {
		try{

		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put(IMAGEN_PARAM,"/"+ opcionService.findByClave(OpcionConstants.IMAGEN_EMPRESA).getValor());
		
		List<Tercero> clientes= cuotaService.findDeudoresMorosos(Calendar.getInstance().getTime());
		
		
		generarReporte(mapa,clientes);
		
		String users=extraxtMailUsers();
		
		mailService.send(
				users,
				"[ Sistema Rentavoz ] - Informe de clientes en mora hasta la fecha ",
				"Buen día , ya está disponible su informe de clientes en mora hasta la fecha ",
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
private void generarReporte(HashMap<String, Object> mapa,List<Tercero> clientes) {
	try { 
		
	StringBuilder reportPath = new StringBuilder(
			opcionService.findByClave(OpcionConstants.PATH_REPORTES).getValor()).append("reportes/").append( REPORT_NAME
			+ REPOT_EXTENSION);
	JasperPrint jasperPrint;
	jasperPrint = JasperFillManager.fillReport(reportPath.toString(),
			mapa, new JRBeanCollectionDataSource(clientes));

	
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
	List<Centrope> centropes=centropeService.findByParametro(ParametrosCentrope.OPERATIVO);
	centropes.addAll(centropeService.findByParametro(ParametrosCentrope.ADMINISTRATIVO));
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
