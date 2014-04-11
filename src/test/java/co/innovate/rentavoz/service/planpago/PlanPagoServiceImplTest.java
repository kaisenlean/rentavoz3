/**
 * 
 */
package co.innovate.rentavoz.service.planpago;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.services.planpago.PlanPagoService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanPagoServiceImplTest
 * @date 10/04/2014
 *
 */
public class PlanPagoServiceImplTest extends BaseUnit{

	
	@Autowired
	private PlanPagoService planPagoService;
	
	
	/**
	 * Test method for {@link co.innovate.rentavoz.services.impl.GenericServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<PlanPago> lista= planPagoService.findAll();
		Assert.assertNotNull(lista);
	}

}
