/**
 * 
 */
package co.innovate.rentavoz.dao.planpago;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.repositories.planpago.PlanPagoDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanPagoDaoImplTest
 * @date 10/04/2014
 *
 */
public class PlanPagoDaoImplTest extends BaseUnit {

	
	@Autowired
	private PlanPagoDao planPagoDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<PlanPago> lista= planPagoDao.findAll();
		Assert.assertNotNull(lista);
		
	}

}
