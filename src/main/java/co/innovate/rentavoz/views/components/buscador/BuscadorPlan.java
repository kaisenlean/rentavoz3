/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.services.plan.PlanService;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class BuscadorPlan
* @date 13/01/2014
*
 */
public abstract class BuscadorPlan extends Buscador<Plan> {

	public abstract PlanService getService();

	public abstract void selCentrope(Plan centrope);

	@Override
	public String buscar() {
		List<Plan> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Plan centrope : result) {
			getItems().add(
					new BuscadorItem<Plan>(centrope.getIdPlan() + "", centrope
							.getPlaNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Plan t) {
		if (t == null) {
			selCentrope(new Plan());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Plan> result = getService().findByCriterio(getCriterio());

		getItems().clear();

		for (Plan centrope : result) {
			getItems().add(
					new BuscadorItem<Plan>(centrope.getIdPlan() + "", centrope
							.getPlaNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
