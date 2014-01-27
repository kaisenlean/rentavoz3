/**
 * 
 */
package co.innovate.rentavoz.test.notificacionlinea;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.services.notificacionlinea.EnvioCorteLineaService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class EnvioCorteLineaServiceImplTest
 * @date 26/01/2014
 *
 */
public class EnvioCorteLineaServiceImplTest extends BaseUnit {

	@Autowired
	private EnvioCorteLineaService envioCorteLineaService;
	/**
	 * Test method for {@link co.innovate.rentavoz.services.notificacionlinea.impl.EnvioCorteLineaServiceImpl#enviarNotificacionLineasFechaCorte()}.
	 */
	@Test
	public void testEnviarNotificacionLineasFechaCorte() {
		envioCorteLineaService.enviarNotificacionLineasFechaCorte();
	}

}
