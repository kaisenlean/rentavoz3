/**
 * 
 */
package co.innovate.rentavoz.dao.consecutivofactura;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.facturacion.ConsecutivoFactura;
import co.innovate.rentavoz.repositories.facturacion.ConsecutivoFacturaDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class ConsecutivoFacturaDaoImplTest
 * @date 21/04/2014
 *
 */
public class ConsecutivoFacturaDaoImplTest extends BaseUnit{

	
	@Autowired
	private ConsecutivoFacturaDao consecutivoFacturaDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		
		List<ConsecutivoFactura> lista= consecutivoFacturaDao.findAll();
		
		assertNotNull(lista);
	}

}
