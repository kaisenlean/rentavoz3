/**
 * 
 */
package co.innovate.rentavoz.dao.permiso.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.permiso.UsuarioMenu;
import co.innovate.rentavoz.repositories.permiso.UsuarioMenuDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class UsuarioMenuDaoImplTest
 * @date 31/01/2014
 *
 */
public class UsuarioMenuDaoImplTest extends BaseUnit{

	@Autowired
	private UsuarioMenuDao usuarioMenuDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
	List<UsuarioMenu> list = usuarioMenuDao.findAll();
	Assert.assertNotNull(list);
	}
	

}
