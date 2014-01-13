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

import co.innovate.rentavoz.model.almacen.MotivoRepoEnum;

@ManagedBean
@ViewScoped
public class SelectorMotivoReposicion  {


	
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		items=new ArrayList<SelectItem>();
	for (int i = 0; i < MotivoRepoEnum.values().length; i++) {
		items.add(new SelectItem(MotivoRepoEnum.values()[i].name(),MotivoRepoEnum.values()[i].name().replace("_", " ") ));
	}
	}
	
	

	public List<SelectItem> getItems() {
		return items;
	}
}
