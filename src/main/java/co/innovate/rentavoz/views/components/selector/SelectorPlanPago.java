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

import co.innovate.rentavoz.model.Cuentas;
import co.innovate.rentavoz.model.planpago.PlanPago;
import co.innovate.rentavoz.services.planpago.PlanPagoService;

/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project rentavoz3
* @class SelectorPlanPago
* @date 11/04/2014
*
*/
@ManagedBean
@ViewScoped
public class SelectorPlanPago implements SelectorBase<Cuentas> {

	@ManagedProperty("#{planPagoService}")
	private PlanPagoService planPagoService;
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<PlanPago> findAll = planPagoService.findAll();
		items = new ArrayList<SelectItem>();
		for (PlanPago empresa : findAll) {
			items.add(new SelectItem(empresa.getId(), empresa
					.getNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 11/04/2014
	 * @param planPagoService the planPagoService to set
	 */
	public void setPlanPagoService(PlanPagoService planPagoService) {
		this.planPagoService = planPagoService;
	}
}
