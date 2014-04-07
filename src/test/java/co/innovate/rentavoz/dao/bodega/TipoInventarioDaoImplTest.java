/**
 * 
 */
package co.innovate.rentavoz.dao.bodega;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.repositories.bodega.TipoInventarioDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventarioDaoImplTest
 * @date 7/04/2014
 *
 */
public class TipoInventarioDaoImplTest extends BaseUnit{

	@Autowired
	private TipoInventarioDao tipoInventarioDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
List<TipoInventario> tipoInventario= tipoInventarioDao.findAll();
		Assert.assertNotNull(tipoInventario);
	}

}
