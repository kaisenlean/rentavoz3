/**
 * 
 */
package co.innovate.rentavoz.service.seguridad;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.seguridad.EncoderManager;
import co.innovate.rentavoz.seguridad.impl.EncoderManagerImpl;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * Unidad de test para la clase {@link EncoderManagerImpl}
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project troca-co
 * @class EncoderManagerImplTest
 * @date 6/11/2013
 * 
 */
public class EncoderManagerImplTest extends BaseUnit {

	/**
	 * 6/11/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         STRING_TO_HASH
	 */
	private static final String STRING_TO_HASH = "123";
	@Autowired
	private EncoderManager encoderManager;

	/**
	 * Test method for
	 * {@link co.innovate.rentavoz.seguridad.impl.EncoderManagerImpl#encodeMd5Hash(java.lang.String)}
	 * .
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/11/2013
	 */
	@Test
	public void testEncodeMd5Hash() {

		String salida = encoderManager.encodeMd5Hash(STRING_TO_HASH);
		assertNotNull(salida);
	}

}
