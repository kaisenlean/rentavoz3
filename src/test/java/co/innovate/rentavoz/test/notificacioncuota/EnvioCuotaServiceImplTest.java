/**
 * 
 */
package co.innovate.rentavoz.test.notificacioncuota;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.services.notificacioncuota.EnvioCuotaService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCuotaServiceImplTest
 * @date 4/03/2014
 *
 */
public class EnvioCuotaServiceImplTest extends BaseUnit {

	@Autowired
	private EnvioCuotaService envioCuotaService;
	
	private Logger logger = Logger.getLogger(EnvioCuotaServiceImplTest.class);
	/**
	 * Test method for {@link co.innovate.rentavoz.services.notificacioncuota.impl.EnvioCuotaServiceImpl#enviarDeudoresMorososHastaLaFecha(java.util.Date)}.
	 */
	@Test
	public void testEnviarDeudoresMorososHastaLaFecha() {
		try {
			
		envioCuotaService.enviarDeudoresMorososHastaLaFecha(Calendar.getInstance().getTime());
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

}
