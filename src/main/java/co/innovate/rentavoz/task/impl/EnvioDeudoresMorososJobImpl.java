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
import co.innovate.rentavoz.task.EnvioDeudoresMorososJob;
import co.innovate.rentavoz.task.EnvioDeudoresMorososTask;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoJobImpl
 * @date 25/02/2014
 *
 */
@Service("envioDeudoresMorososJob")
public class EnvioDeudoresMorososJobImpl extends QuartzJobBean implements EnvioDeudoresMorososJob {

	@Autowired
	private EnvioDeudoresMorososTask envioDeudoresMorososTask;
	
	
	private Logger logger= Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.task.EnvioLineaConsumoJob#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	public void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			envioDeudoresMorososTask.enviarDeudoresMorosos();
		} catch (BaseException e) {
			logger.error(e);
		}
		
	}
	
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 4/03/2014
 * @param envioDeudoresMorososTask the envioDeudoresMorososTask to set
 */
public void setEnvioDeudoresMorososTask(
		EnvioDeudoresMorososTask envioDeudoresMorososTask) {
	this.envioDeudoresMorososTask = envioDeudoresMorososTask;
}

}
