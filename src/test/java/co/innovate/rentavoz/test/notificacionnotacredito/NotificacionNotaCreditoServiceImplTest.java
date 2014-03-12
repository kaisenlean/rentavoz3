/**
 * 
 */
package co.innovate.rentavoz.test.notificacionnotacredito;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.services.notificacionnotacredito.NotificacionNotaCreditoService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotificacionNotaCreditoServiceImplTest
 * @date 12/03/2014
 *
 */
public class NotificacionNotaCreditoServiceImplTest extends BaseUnit{

	@Autowired
	private NotificacionNotaCreditoService notificacionNotaCreditoService;
	/**
	 * Test method for {@link co.innovate.rentavoz.services.notificacionnotacredito.impl.NotificacionNotaCreditoServiceImpl#enviarNotificacionNotasCredito()}.
	 */
	@Test
	public void testEnviarNotificacionNotasCredito() {
		notificacionNotaCreditoService.enviarNotificacionNotasCredito();
	}

}
