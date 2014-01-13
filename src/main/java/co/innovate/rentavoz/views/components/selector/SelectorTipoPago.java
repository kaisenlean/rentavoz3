/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.selector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.innovate.rentavoz.model.TipoPago;
import co.innovate.rentavoz.services.tipopago.TipoPagoService;

@ManagedBean
@ViewScoped
public class SelectorTipoPago implements SelectorBase<TipoPago> {

	@EJB
	private TipoPagoService tipoPagoService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<TipoPago> findAll = tipoPagoService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un tipo de pago --"));
		for (TipoPago empresa : findAll) {
			items.add(new SelectItem(empresa.getIdTipoPago(), empresa
					.getTPagoNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
