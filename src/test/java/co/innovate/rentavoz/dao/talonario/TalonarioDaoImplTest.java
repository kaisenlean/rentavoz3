/**
 * 
 */
package co.innovate.rentavoz.dao.talonario;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.facturacion.Talonario;
import co.innovate.rentavoz.repositories.facturacion.TalonarioDao;
import co.innovate.rentavoz.repositories.sucursal.SucursalDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TalonarioDaoImplTest
 * @date 21/04/2014
 *
 */
public class TalonarioDaoImplTest extends BaseUnit{

	/**
	 * 21/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * COD_SUCURSAL
	 */
	private static final int COD_SUCURSAL = 2;

	@Autowired
	private TalonarioDao talonarioDao;
	
	@Autowired
	private SucursalDao sucursalDao;
	
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Talonario> lista = talonarioDao.findAll();
		assertNotNull(lista);
	}
	
	@Test
	public void testCargarConsecutivoFactura() {
		try {
			
		Talonario talonario = talonarioDao.cargarConsecutivoFactura(sucursalDao.findById(COD_SUCURSAL));
		assertNotNull(talonario);
		} catch (Exception e) {
		logger.error(e);
		}
	}
	
	

}
