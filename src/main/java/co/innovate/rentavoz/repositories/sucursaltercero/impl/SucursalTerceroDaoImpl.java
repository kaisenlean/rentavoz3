/**
 * 
 */
package co.innovate.rentavoz.repositories.sucursaltercero.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.SucursalTercero;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.sucursaltercero.SucursalTerceroDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class SucursalTerceroDaoImpl
 * @date 2/02/2014
 *
 */
@Repository("sucursalTerceroDao")
public class SucursalTerceroDaoImpl extends GenericJpaRepository<SucursalTercero, Integer> implements Serializable,SucursalTerceroDao {

	/**
	 * 2/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.sucursaltercero.SucursalTerceroDao#findByTercero()
	 */
	@Override
	public List<SucursalTercero> findByTercero(Tercero tercero) {
		Criterion criterion = Restrictions.eq("terceroidTecero", tercero);
		return findByCriteria(criterion);
	}

}
