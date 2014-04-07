/**
 * 
 */
package co.innovate.rentavoz.service.bodega;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.services.bodega.TipoInventarioService;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TipoInventarioServiceImplTest
 * @date 7/04/2014
 *
 */
public class TipoInventarioServiceImplTest extends BaseUnit {

	@Autowired
	private TipoInventarioService tipoInventarioService;
	
	/**
	 * Test method for {@link co.innovate.rentavoz.services.impl.GenericServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
	List<TipoInventario> lista = tipoInventarioService.findAll();
	Assert.assertNotNull(lista);
	}

}
