/**
 * 
 */
package co.innovate.rentavoz.repositories.tercero.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.model.Tercero;
import co.innovate.rentavoz.model.TipoTerceroEnum;
import co.innovate.rentavoz.model.profile.Usuario;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.tercero.TerceroDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz-3
 * @class TerceroDaoImpl
 * @date 12/01/2014
 * 
 */
@Repository("terceroDao")
public class TerceroDaoImpl extends GenericJpaRepository<Tercero, Integer>
		implements TerceroDao, Serializable {

	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * CENTROPE
	 */
	private static final String CENTROPE = "centrope";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ZERO
	 */
	private static final int ZERO = 0;
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TER_DOCUMENTO
	 */
	private static final String TER_DOCUMENTO = "terDocumento";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TER_NOMBRE
	 */
	private static final String TER_NOMBRE = "terNombre";
	/**
	 * 12/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TER_APELLIDOS
	 */
	private static final String TER_APELLIDOS = "terApellidos";
	/**
	 * 12/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.repositories.tercero.TerceroDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Tercero> findByCriterio(String criterio) {
		Criterion criterion = Restrictions
				.disjunction()
				.add(Restrictions.like(TER_APELLIDOS, criterio,
						MatchMode.ANYWHERE))
				.add(Restrictions.like(TER_NOMBRE, criterio,
						MatchMode.ANYWHERE))
				.add(Restrictions.like(TER_DOCUMENTO, criterio,
						MatchMode.ANYWHERE));
		return findByCriteria(criterion);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.tercero.TerceroDao#findByDocumento(
	 * java.lang.String)
	 */
	@Override
	public Tercero findByDocumento(String val) {
		
		Criterion criterion = Restrictions.eq(TER_DOCUMENTO, val);
		List<Tercero> terceros= findByCriteria(criterion);
		if (terceros.isEmpty()) {
			return null;
		} else {
			return terceros.get(ZERO);

		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.tercero.TerceroDao#findByUsuario(co
	 * .innovate.rentavoz.model.profile.Usuario)
	 */
	@Override
	public Tercero findByUsuario(Usuario user) {
		Criterion criterion = Restrictions.eq("usuario", user);
		List<Tercero> terceros = findByCriteria(criterion);
		if (!terceros.isEmpty()) {
			return terceros.get(ZERO);
		} else {
			return null;

		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.tercero.TerceroDao#findByCentrope(int)
	 */
	@Override
	public List<Tercero> findByCentrope(Centrope idCentrope) {
		Criterion criterion= Restrictions.eq(CENTROPE, idCentrope);
		return findByCriteria(criterion);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.repositories.tercero.TerceroDao#findByCriterioProveedor
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tercero> findByCriterioProveedor(String criterio) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT t FROM Tercero t WHERE t.tipo = :tipoTercero AND  t.terApellidos LIKE :criterio OR t.terNombre LIKE :criterio  OR t.terDocumento LIKE :criterio");
		q.setParameter("criterio", "%" + criterio + "%");
		q.setParameter("tipoTercero", TipoTerceroEnum.PROVEEDOR  );
		return q.getResultList();
	}
	



}
