/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.selector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.innovate.rentavoz.model.Plan;
import co.innovate.rentavoz.services.plan.PlanService;

@ManagedBean
@ViewScoped
public class SelectorPlan implements SelectorBase<Plan> {

	@ManagedProperty(value="#{planService}")
	private PlanService planService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Plan> findAll = planService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un Plán --"));
		for (Plan empresa : findAll) {
			items.add(new SelectItem(empresa.getIdPlan(), empresa
					.getPlaNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param planService the planService to set
	 */
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
}
