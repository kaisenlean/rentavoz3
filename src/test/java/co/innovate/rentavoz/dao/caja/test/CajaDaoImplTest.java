/**
 * 
 */
package co.innovate.rentavoz.dao.caja.test;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.repositories.caja.CajaDao;
import co.innovate.rentavoz.repositories.sucursal.SucursalDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CajaDaoImplTest
 * @date 27/02/2014
 *
 */
public class CajaDaoImplTest extends BaseUnit {

	
	 /**
	 * 27/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * CODE_SUCURSAL
	 */
	private static final int CODE_SUCURSAL = 2;

	@Autowired
	 private SucursalDao sucursalDao;
	 
	 @Autowired
	 private CajaDao cajaDao;
	 
	 private Logger logger= Logger.getLogger(CajaDaoImplTest.class);
	 
	 
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.caja.impl.CajaDaoImpl#valorCajaLineasBySucursal(co.innovate.rentavoz.model.Sucursal, java.util.Date)}.
	 */
	@Test
	public void testValorCajaLineasBySucursal() {
		
		Sucursal sucursal = sucursalDao.findById(CODE_SUCURSAL);
		assertNotNull(sucursal);
		
		Double valorCaja= cajaDao.valorCajaLineasBySucursal(sucursal, Calendar.getInstance().getTime());
		
		assertNotNull(valorCaja);
		logger.info("*******valor en caja : ".concat(valorCaja.toString()));
		
	}

}
