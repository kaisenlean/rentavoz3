/**
 * 
 */
package co.innovate.rentavoz.service.permiso.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.services.permiso.UsuarioMenuService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UsuarioMenuServiceImplTest
 * @date 31/01/2014
 *
 */
public class UsuarioMenuServiceImplTest extends BaseUnit{

	@Autowired
	private UsuarioMenuService menuService;
	/**
	 * Test method for {@link co.innovate.rentavoz.services.impl.GenericServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
	
	assertNotNull(menuService.findAll());
	}

}
