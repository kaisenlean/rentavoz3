/**
 * 
 */
package co.innovate.rentavoz.repositories.tarea.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.exception.BaseException;
import co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum;
import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.tarea.EstadoTareaEnum;
import co.innovate.rentavoz.model.tarea.Tarea;
import co.innovate.rentavoz.repositories.centrope.CentropeDao;
import co.innovate.rentavoz.repositories.centrope.ParametrosCentrope;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.tarea.TareaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class TareaDaoImpl
 * @date 14/03/2014
 *
 */
@Repository("tareaDao")
public class TareaDaoImpl extends GenericJpaRepository<Tarea, Integer>  implements TareaDao,Serializable {

	/**
	 * 15/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ESTADO
	 */
	private static final String ESTADO = "estado";
	/**
	 * 15/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * OWNER
	 */
	private static final String OWNER = "owner";
	@Autowired
	private CentropeDao centropeDao;
	/**
	 * 14/03/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.tarea.TareaDao#findTareasPendientes(co.innovate.rentavoz.logic.tarea.impl.TareaCentropeEnum)
	 */
	@Override
	public List<Tarea> findTareasPendientes(TareaCentropeEnum centropeEnum) throws BaseException {
		
		
		Centrope centrope=null;
		List<Centrope> lista=null;
		switch (centropeEnum) {
		case ADMINISTRATIVO:
			
	 lista=centropeDao.findByParametro(ParametrosCentrope.ADMINISTRATIVO);
			break;
			
	case OPERATIVO:
		lista=centropeDao.findByParametro(ParametrosCentrope.ADMINISTRATIVO);
		
		break;

		default:
			break;
		}
		
		if (lista.isEmpty()) {
			throw new BaseException("Por favor especifica los parametrso especiales para los centros de operacion (operativ o administrativ)");
		}
		
		centrope=lista.get(BigInteger.ZERO.intValue());
		
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq(OWNER,centrope)).add(Restrictions.eq(ESTADO, EstadoTareaEnum.PENDIENTE));
		
		return findByCriteria(criterion);
	}

}
