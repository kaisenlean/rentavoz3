/**
 * 
 */
package co.innovate.rentavoz.dao.lineaconsumo.test;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.almacen.linea.consumo.LineaConsumoDao;
import co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class LineaConsumoDaoImplTest
 * @date 25/02/2014
 *
 */
public class LineaConsumoDaoImplTest extends BaseUnit {

	
	@Autowired
	private LineaConsumoDao lineaConsumoDao;
	
	
	@Autowired
	private FechaFacturacionDao  fechaFacturacionDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.almacen.linea.consumo.impl.LineaConsumoDaoImpl#findLineasNoVendidasConConsumo(co.innovate.rentavoz.model.facturacion.FechaFacturacion, int)}.
	 */
	@Test
	public void testFindLineasNoVendidasConConsumo() {
		FechaFacturacion periodo = fechaFacturacionDao.findByFecha(Calendar.getInstance().getTime());
		List<Linea> lineas = lineaConsumoDao.findLineasNoVendidasConConsumo(periodo, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		
		Assert.notNull(lineas);
	}

}
