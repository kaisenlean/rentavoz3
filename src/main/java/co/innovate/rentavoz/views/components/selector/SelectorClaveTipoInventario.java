/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.innovate.rentavoz.views.components.selector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.innovate.rentavoz.model.bodega.ClaveTipoInventarioEnum;

@ManagedBean
@ViewScoped
public class SelectorClaveTipoInventario implements Serializable {

	/**
	 * 7/04/2014
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		items=new ArrayList<SelectItem>();
	for (int i = 0; i < ClaveTipoInventarioEnum.values().length; i++) {
		items.add(new SelectItem(ClaveTipoInventarioEnum.values()[i].name(),ClaveTipoInventarioEnum.values()[i].name().replace("_", " ") ));
	}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
