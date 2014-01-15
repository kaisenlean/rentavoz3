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

import co.innovate.rentavoz.model.bodega.BodegaItem;
import co.innovate.rentavoz.services.bodegaitem.BodegaItemService;

@ManagedBean
@ViewScoped
public class SelectorItem implements SelectorBase<BodegaItem> {

	@ManagedProperty(value="#{bodegaItemService}")
	private BodegaItemService bodegaItemService;
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<BodegaItem> findAll = bodegaItemService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem("0", "-- Seleccione un item --"));
		for (BodegaItem estadoLinea : findAll) {
			items.add(new SelectItem(estadoLinea.getId() + "",
					estadoLinea.getNombre()+" "+estadoLinea.getReferencia()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/01/2014
	 * @param bodegaItemService the bodegaItemService to set
	 */
	public void setBodegaItemService(BodegaItemService bodegaItemService) {
		this.bodegaItemService = bodegaItemService;
	}
}
