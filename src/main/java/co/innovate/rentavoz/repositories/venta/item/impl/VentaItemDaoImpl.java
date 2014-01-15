/**
 * 
 */
package co.innovate.rentavoz.repositories.venta.item.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.venta.VentaItem;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.venta.item.VentaItemDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class VentaItemDaoImpl
 * @date 15/01/2014
 *
 */
@Repository("ventaItemDao")
public class VentaItemDaoImpl extends GenericJpaRepository<VentaItem, Integer> implements Serializable,VentaItemDao
{

	/**
	 * 15/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
