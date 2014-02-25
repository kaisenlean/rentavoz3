package co.innovate.rentavoz.task.impl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.task.EnvioLineaCorteJob;
import co.innovate.rentavoz.task.EnvioLineaCorteTask;
 
@Service("envioLineaCorteJob")
public class EnvioLineaCorteJobImpl extends QuartzJobBean implements EnvioLineaCorteJob {
	
	@Autowired
	private EnvioLineaCorteTask envioLineaCorteTask;
 
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @param envioLineaCorteTask the envioLineaCorteTask to set
	 */
	public void setEnvioLineaCorteTask(EnvioLineaCorteTask envioLineaCorteTask) {
		this.envioLineaCorteTask = envioLineaCorteTask;
	}
 
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 25/02/2014
	 * @return the envioLineaCorteTask
	 */
	public EnvioLineaCorteTask getEnvioLineaCorteTask() {
		return envioLineaCorteTask;
	}



	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	public void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		envioLineaCorteTask.printMe();
	}
	
	
	
	
	
	
	
	
}