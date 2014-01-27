package co.innovate.rentavoz.task.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.cron.CronActivity;
import co.innovate.rentavoz.model.cron.CronActivityParametro;
import co.innovate.rentavoz.services.cron.CronActivityService;
import co.innovate.rentavoz.services.notificacionlinea.EnvioCorteLineaService;

@Service("runMeTask")
public class RunMeTaskImpl  implements co.innovate.rentavoz.task.RunMeTask{

	private Logger logger= Logger.getLogger(RunMeTaskImpl.class);
	
	@Autowired
	private EnvioCorteLineaService envioCorteLineaService;
	
	@Autowired
	private CronActivityService cronActivityService;
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.task.RunMeTask#printMe()
	 */
	public void printMe() {
		logger.info("====== Iniciando recorrido =========");
		DateFormat format= new SimpleDateFormat("HH:mm");
		CronActivity cronActivity= cronActivityService.findById(CronActivityParametro.ENVIO_LINEA_CORTE);
		if (cronActivity==null) {
			return;
		}
		
		String nowTime=format.format(Calendar.getInstance().getTime());
		if (cronActivity.getMultiEjecucion()) {
			
		List<String> jobTimes= Arrays.asList(cronActivity.getEjecuciones().split(";"));
		
		if (jobTimes.contains(nowTime)) {
			logger.info("====== Iniciando Actividad programada =========");
			envioCorteLineaService.enviarNotificacionLineasFechaCorte();
			logger.info("====== Fin de la actividad =========");
		}
		
		}else{
		String schedule=format.format(cronActivity.getHoraInicio());
		
		if (schedule.equals(nowTime)) {
			logger.info("====== Iniciando Actividad programada =========");
			envioCorteLineaService.enviarNotificacionLineasFechaCorte();
			logger.info("====== Fin de la actividad =========");
		}
		}
	}
	
	
}