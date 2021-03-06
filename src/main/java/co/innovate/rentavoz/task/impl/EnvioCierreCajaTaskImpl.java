/**
 * 
 */
package co.innovate.rentavoz.task.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.cron.CronActivity;
import co.innovate.rentavoz.model.cron.CronActivityParametro;
import co.innovate.rentavoz.services.cron.CronActivityService;
import co.innovate.rentavoz.services.notificacioncaja.EnvioCierreCajaService;
import co.innovate.rentavoz.services.notificacionnotacredito.NotificacionNotaCreditoService;
import co.innovate.rentavoz.task.EnvioCierreCajaTask;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoTaskImpl
 * @date 25/02/2014
 * 
 */
@Service("envioCierreCajaTask")
public class EnvioCierreCajaTaskImpl implements EnvioCierreCajaTask,
		Serializable {

	/**
	 * 25/02/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CronActivityService cronActivityService;

	@Autowired
	private EnvioCierreCajaService envioCierreCajaService;
	
	@Autowired
	private NotificacionNotaCreditoService notificacionNotaCreditoService;

	private Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.task.EnvioLineaConsumoTask#enviarConsumosLineasSinVender
	 * ()
	 */
	@Override
	public void enviarCierreDeCaja() throws BaseException {
		logger.info("====== Iniciando Busqueda de Ejecuciones =========");
		DateFormat format = new SimpleDateFormat("HH:mm");
		CronActivity cronActivity = cronActivityService
				.findById(CronActivityParametro.ENVIO_CIERRE_CAJA);
		if (cronActivity == null) {
			return;
		}

		String nowTime = format.format(Calendar.getInstance().getTime());
		if (cronActivity.getMultiEjecucion()) {

			List<String> jobTimes = Arrays.asList(cronActivity.getEjecuciones()
					.split(";"));

			if (jobTimes.contains(nowTime)) {
				logger.info("====== Iniciando busqueda =========");
				envioCierreCajaService.enviarCierreCajaAdministrativo();
				logger.info("====== Fin de la actividad =========");
			}

		} else {
			String schedule = format.format(cronActivity.getHoraInicio());

			if (schedule.equals(nowTime)) {
				logger.info("====== Iniciando Actividad programada =========");
				envioCierreCajaService.enviarCierreCajaAdministrativo();
				logger.info("====== Fin de la actividad =========");
			}
		}
		
		 cronActivity = cronActivityService
					.findById(CronActivityParametro.ENVIO_NOTAS_CREDITO);
		 if (cronActivity == null) {
				return;
			}

			 nowTime = format.format(Calendar.getInstance().getTime());
			if (cronActivity.getMultiEjecucion()) {

				List<String> jobTimes = Arrays.asList(cronActivity.getEjecuciones()
						.split(";"));

				if (jobTimes.contains(nowTime)) {
					logger.info("====== Iniciando busqueda =========");
					notificacionNotaCreditoService.enviarNotificacionNotasCredito();
					logger.info("====== Fin de la actividad =========");
				}

			} else {
				String schedule = format.format(cronActivity.getHoraInicio());

				if (schedule.equals(nowTime)) {
					logger.info("====== Iniciando Actividad programada =========");
					notificacionNotaCreditoService.enviarNotificacionNotasCredito();
					logger.info("====== Fin de la actividad =========");
				}
			}

	}

}
