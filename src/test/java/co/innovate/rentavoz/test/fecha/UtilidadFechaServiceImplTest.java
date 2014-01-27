/**
 * 
 */
package co.innovate.rentavoz.test.fecha;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.services.fecha.UtilidadFechaService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UtilidadFechaServiceImplTest
 * @date 26/01/2014
 *
 */
public class UtilidadFechaServiceImplTest  extends BaseUnit{

	@Autowired
	private UtilidadFechaService utilidadFechaService;
	
	private Logger logger= Logger.getLogger(UtilidadFechaServiceImplTest.class);
	/**
	 * Test method for {@link co.innovate.rentavoz.services.fecha.impl.UtilidadFechaServiceImpl#obtenerMesesPorRangoDeFechas(java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testObtenerMesesPorRangoDeFechas() {

		try {
			
		Calendar calendar= Calendar.getInstance();
		
		Date inicio=calendar.getTime();
		
		calendar.add(Calendar.MONTH,1);
		Date fin= calendar.getTime();
		
		
		logger.info(utilidadFechaService.obtenerMesesPorRangoDeFechas(inicio, fin));
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
