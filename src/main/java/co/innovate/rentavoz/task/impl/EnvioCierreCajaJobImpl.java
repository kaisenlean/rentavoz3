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
import co.innovate.rentavoz.task.EnvioCierreCajaJob;
import co.innovate.rentavoz.task.EnvioCierreCajaTask;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioLineaConsumoJobImpl
 * @date 25/02/2014
 *
 */
@Service("envioCierreCajaJob")
public class EnvioCierreCajaJobImpl extends QuartzJobBean implements EnvioCierreCajaJob {

	@Autowired
	private EnvioCierreCajaTask envioCierreCajaTask;
	
	
	private Logger logger= Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.task.EnvioLineaConsumoJob#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	public void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
		envioCierreCajaTask.enviarCierreDeCaja();
		} catch (BaseException e) {
			logger.error(e);
		}
		
	}
	
	
/**
 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 1/03/2014
 * @param envioCierreCajaTask the envioCierreCajaTask to set
 */
public void setEnvioCierreCajaTask(EnvioCierreCajaTask envioCierreCajaTask) {
	this.envioCierreCajaTask = envioCierreCajaTask;
}

}
