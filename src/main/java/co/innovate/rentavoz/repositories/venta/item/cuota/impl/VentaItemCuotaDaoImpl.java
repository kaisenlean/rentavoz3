/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.cuota.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.venta.VentaItemCuota;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.venta.item.cuota.VentaItemCuotaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemCuotaDaoImpl
 * @date 15/01/2014
 *
 */
@Repository("ventaItemCuota")
public class VentaItemCuotaDaoImpl extends GenericJpaRepository<VentaItemCuota, Integer> implements VentaItemCuotaDao,Serializable {

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
