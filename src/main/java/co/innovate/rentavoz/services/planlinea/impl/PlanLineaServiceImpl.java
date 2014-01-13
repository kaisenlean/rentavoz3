/**
 * 
 */
package co.innovate.rentavoz.services.planlinea.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.model.PlanLinea;
import co.innovate.rentavoz.model.almacen.Linea;
import co.innovate.rentavoz.repositories.GenericRepository;
import co.innovate.rentavoz.repositories.planlinea.PlanLineaDao;
import co.innovate.rentavoz.services.impl.GenericServiceImpl;
import co.innovate.rentavoz.services.planlinea.PlanLineaService;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project rentavoz3
 * @class PlanLineaServiceImpl
 * @date 13/01/2014
 * 
 */
@Service("planLineaService")
public class PlanLineaServiceImpl extends
		GenericServiceImpl<PlanLinea, Integer> implements Serializable,
		PlanLineaService {

	@Autowired
	private PlanLineaDao planLineaDao;
	/**
	 * 13/01/2014
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.services.planlinea.PlanLineaService#
	 * desactivarTodosPlanesLineas(co.innovate.rentavoz.model.almacen.Linea)
	 */
	@Override
	public void desactivarTodosPlanesLineas(Linea l) {
		planLineaDao.desactivarTodosPlanesLineas(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.innovate.rentavoz.services.planlinea.PlanLineaService#activarPorLineaYPlan
	 * (co.innovate.rentavoz.model.almacen.Linea,
	 * co.innovate.rentavoz.model.Plan)
	 */
	@Override
	public void activarPorLineaYPlan(Linea l, Plan p) {
		planLineaDao.activarPorLineaYPlan(l, p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.innovate.rentavoz.services.impl.GenericServiceImpl#getDao()
	 */
	@Override
	public GenericRepository<PlanLinea, Integer> getDao() {
		return planLineaDao;
	}

}
