/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.selector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.innovate.rentavoz.model.almacen.TipoTrasladoEnum;

@ManagedBean
@ViewScoped
public class SelectorTipoTraslado  {

	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
	for (int i = 0; i < TipoTrasladoEnum.values().length; i++) {
		items.add(new SelectItem(TipoTrasladoEnum.values()[i].name(),TipoTrasladoEnum.values()[i].name().replace("_", " ") ));
	}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
