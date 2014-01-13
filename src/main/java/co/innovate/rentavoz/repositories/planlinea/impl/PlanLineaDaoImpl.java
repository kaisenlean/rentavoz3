/**
 * 
 */
package co.innovate.rentavoz.repositories.planlinea.impl;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.PlanLinea;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.repositories.impl.GenericJpaRepository;
import co.innovate.rentavoz.repositories.planlinea.PlanLineaDao;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanLineaDaoImpl
 * @date 13/01/2014
 *
 */
@Repository("planLineaDao")
public class PlanLineaDaoImpl extends GenericJpaRepository<PlanLinea, Integer> implements PlanLineaDao,Serializable {

	/**
	 * 13/01/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.planlinea.PlanLineaDao#desactivarTodosPlanesLineas(co.innovate.rentavoz.model.almacen.Linea)
	 */
	public void desactivarTodosPlanesLineas(Linea l) {
		Query q = getEntityManager()
				.createQuery(
						"UPDATE PlanLinea pl SET pl.plaEstado=0 WHERE pl.lineaidLinea = :linea");
		q.setParameter("linea", l);
		q.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see co.innovate.rentavoz.repositories.planlinea.PlanLineaDao#activarPorLineaYPlan(co.innovate.rentavoz.model.almacen.Linea, co.innovate.rentavoz.model.Plan)
	 */
	public void activarPorLineaYPlan(Linea l, Plan p) {
		Query q = getEntityManager()
				.createQuery(
						"UPDATE PlanLinea pl SET pl.plaEstado=1 WHERE pl.lineaidLinea = :linea AND pl.planidPlan = :plan");
		q.setParameter("linea", l);
		q.setParameter("plan", p);
		q.executeUpdate();

	}

}
