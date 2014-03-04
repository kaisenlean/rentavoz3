package co.innovate.rentavoz.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
 
public interface EnvioDeudoresMorososJob  {
	
	
	void executeInternal(JobExecutionContext context)
				throws JobExecutionException;
	
}