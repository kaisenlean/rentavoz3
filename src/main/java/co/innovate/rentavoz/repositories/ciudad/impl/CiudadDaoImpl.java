/**
 * 
 */
package co.innovate.rentavoz.repositories.ciudad.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Ciudad;
import co.innovate.rentavoz.model.Departamento;
import co.innovate.rentavoz.repositories.ciudad.CiudadDao;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class CiudadDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("ciudadDao")
public class CiudadDaoImpl extends GenericJpaRepository<Ciudad, Integer> implements CiudadDao , Serializable {

	/**
	 * 1/02/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * CIU_NOMBRE
	 */
	private static final String CIU_NOMBRE = "ciuNombre";
	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.ciudad.CiudadDao#findByCriterio(java.lang.String, co.innovate.rentavoz.model.Departamento)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ciudad> findByCriterio(String criterio,
			Departamento departamento) {
	Session session = (Session) getEntityManager().unwrap(Session.class);
	Criteria critaria=session.createCriteria(Ciudad.class);
	Criterion criterion= Restrictions.conjunction().add(Restrictions.like(CIU_NOMBRE, criterio,MatchMode.ANYWHERE)).add(Restrictions.eq("departamentoidDepartamento", departamento));
	critaria.add(criterion);
	critaria.createAlias("departamentoidDepartamento", "departamentoidDepartamento");
	critaria.addOrder(Order.asc("departamentoidDepartamento.depNombre"));
		return critaria.list();
				
	}
	
	
	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.impl.GenericJpaRepository#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ciudad> findAll() {

		Query q = getEntityManager()
				.createQuery(
						"SELECT c FROM Ciudad c ORDER BY c.departamentoidDepartamento.depNombre ASC");
		return new ArrayList<Ciudad>(q.getResultList());
		}


	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.ciudad.CiudadDao#findByCriterio(java.lang.String)
	 */
	@Override
	public List<Ciudad> findByCriterio(String criterio) {
		Criterion criterion=Restrictions.like(CIU_NOMBRE, criterio,MatchMode.ANYWHERE);
		return findByCriteria(criterion);
	}

}
