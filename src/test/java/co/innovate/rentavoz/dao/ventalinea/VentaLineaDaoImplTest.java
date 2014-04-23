/**
 * 
 */
package co.innovate.rentavoz.dao.ventalinea;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.innovate.rentavoz.model.almacen.venta.Venta;
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.repositories.almacen.venta.VentaLineaDao;
import co.innovate.rentavoz.repositories.facturacion.FechaFacturacionDao;
import co.innovate.rentavoz.test.BaseUnit;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaLineaDaoImplTest
 * @date 23/04/2014
 *
 */
public class VentaLineaDaoImplTest extends BaseUnit {

	
	@Autowired
	private FechaFacturacionDao fechaFacturacionDao;
	
	
	@Autowired
	private VentaLineaDao ventaLineaDao;
	/**
	 * Test method for {@link co.innovate.rentavoz.repositories.almacen.venta.impl.VentaLineaDaoImpl#findVentaAjuste(co.innovate.rentavoz.model.facturacion.FechaFacturacion, java.lang.Boolean)}.
	 */
	@Test
	public void testFindVentaAjuste() {
		FechaFacturacion facturacion= fechaFacturacionDao.findByFecha(Calendar.getInstance().getTime());
		List<Venta> ventas = ventaLineaDao.findVentaAjuste(facturacion, Boolean.FALSE);
		Assert.assertNotNull(ventas);
	
	}

}
