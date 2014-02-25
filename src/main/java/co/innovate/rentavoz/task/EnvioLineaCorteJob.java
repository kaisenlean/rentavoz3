package co.innovate.rentavoz.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
 
public interface EnvioLineaCorteJob  {
	
	
	void executeInternal(JobExecutionContext context)
				throws JobExecutionException;
	
}