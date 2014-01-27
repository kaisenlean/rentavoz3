package co.innovate.rentavoz.task.impl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.task.RunMeJob;
 
@Service("runMeJob")
public class RunMeJobImpl extends QuartzJobBean implements RunMeJob {
	
	@Autowired
	private RunMeTaskImpl runMeTask;
 
	public void setRunMeTask(RunMeTaskImpl runMeTask) {
		this.runMeTask = runMeTask;
	}
 
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @return the runMeTask
	 */
	public RunMeTaskImpl getRunMeTask() {
		return runMeTask;
	}



	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	public void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		runMeTask.printMe();
	}
	
	
	
	
	
	
	
	
}