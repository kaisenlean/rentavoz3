/**
 * 
 */
package co.innovate.rentavoz.repositories.opcion.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.model.Opcion;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.opcion.OpcionDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class OpcionDaoImpl
 * @date 18/01/2014
 *
 */
@Repository("opcionDao")
public class OpcionDaoImpl extends GenericJpaRepository<Opcion, String> implements OpcionDao,Serializable {

	/**
	 * 18/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * FIELD_CODIGO
	 */
	private static final String FIELD_CODIGO = "codigo";
	/**
	 * 18/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.opcion.OpcionDao#findByClave(java.lang.String)
	 */
	@Override
	public Opcion findByClave(String clave) throws BaseException {
		Criterion criterion = Restrictions.eq(FIELD_CODIGO, clave);
		List<Opcion> listado= findByCriteria(criterion);
		
		if (listado.isEmpty()) {
			return null;
		}
		return listado.get(BigInteger.ZERO.intValue());
	}
	
	
	

}
