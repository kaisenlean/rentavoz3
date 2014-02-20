/**
 * 
 */
package co.innovate.rentavoz.service.consumo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.logic.consumo.ConsumoControllerService;
import co.innovate.rentavoz.model.almacen.LineaConsumo;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsumoControllerServiceImplTest
 * @date 19/02/2014
 *
 */

public class ConsumoControllerServiceImplTest extends BaseUnit {

	
	@Autowired
	private ConsumoControllerService consumoControllerService;
	/**
	 * Test method for {@link co.innovate.rentavoz.logic.consumo.impl.ConsumoControllerServiceImpl#cargarConsumosClaro(java.lang.String)}.
	 */
	@Test
	public void testCargarConsumosClaro() {
		List<LineaConsumo> consumos= consumoControllerService.cargarConsumosClaro("/home/ejody/Escritorio/formato_consumos_claro.csv");
		Assert.assertNotNull(consumos);
	}

}
