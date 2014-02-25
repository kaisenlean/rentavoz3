/**
 * 
 */
package co.innovate.rentavoz.task.impl;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.task.EnvioLineaConsumoJob;
import co.innovate.rentavoz.task.EnvioLineaConsumoTask;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoJobImpl
 * @date 25/02/2014
 *
 */
@Service("envioLineaConsumoJob")
public class EnvioLineaConsumoJobImpl extends QuartzJobBean implements EnvioLineaConsumoJob {

	@Autowired
	private EnvioLineaConsumoTask envioLineaConsumoTask;
	
	
	private Logger logger= Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.task.EnvioLineaConsumoJob#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	public void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			envioLineaConsumoTask.enviarConsumosLineasSinVender();
		} catch (BaseException e) {
			logger.error(e);
		}
		
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @param envioLineaConsumoTask the envioLineaConsumoTask to set
	 */
	public void setEnvioLineaConsumoTask(
			EnvioLineaConsumoTask envioLineaConsumoTask) {
		this.envioLineaConsumoTask = envioLineaConsumoTask;
	}

}
