package co.innovate.rentavoz.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
 
public interface EnvioLineaConsumoJob  {
	
	
	void executeInternal(JobExecutionContext context)
				throws JobExecutionException;
	
}