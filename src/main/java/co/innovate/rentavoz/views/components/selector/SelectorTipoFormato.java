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

import co.innovate.rentavoz.model.formato.TipoFormatoEnum;

@ManagedBean
@ViewScoped
public class SelectorTipoFormato  {


	
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		items=new ArrayList<SelectItem>();
	for (int i = 0; i < TipoFormatoEnum.values().length; i++) {
		items.add(new SelectItem(TipoFormatoEnum.values()[i].name(),TipoFormatoEnum.values()[i].name().replace("_", " ") ));
	}
	}
	
	

	public List<SelectItem> getItems() {
		return items;
	}
}
