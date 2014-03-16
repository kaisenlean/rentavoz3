/**
 * 
 */
package co.innovate.rentavoz.dao.facturacion.notacredito;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.repositories.facturacion.NotaCreditoDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class NotaCreditoDaoImplTest
 * @date 11/03/2014
 *
 */
public class NotaCreditoDaoImplTest  extends BaseUnit{

	
	
	@Autowired
	private NotaCreditoDao notaCreditoDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		assertNotNull(notaCreditoDao.findAll());
	}
	
	@Test
	public void testFindBYFecha(){
		
		assertNotNull(notaCreditoDao.findByFecha(Calendar.getInstance().getTime()));
	}

}
