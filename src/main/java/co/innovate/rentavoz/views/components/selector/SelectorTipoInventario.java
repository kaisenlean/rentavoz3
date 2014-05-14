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

import co.innovate.rentavoz.model.bodega.TipoInventario;
import co.innovate.rentavoz.services.bodega.TipoInventarioService;

@ManagedBean
@ViewScoped
public class SelectorTipoInventario implements SelectorBase<TipoInventario> {

	@ManagedProperty("#{tipoInventarioService}")
	private TipoInventarioService tipoInventarioService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<TipoInventario> findAll = tipoInventarioService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem("", "-- Seleccione un tipo de inventario --"));
		for (TipoInventario empresa : findAll) {
			items.add(new SelectItem(empresa.getId(), empresa
					.getNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/04/2014
	 * @param tipoInventarioService the tipoInventarioService to set
	 */
	public void setTipoInventarioService(
			TipoInventarioService tipoInventarioService) {
		this.tipoInventarioService = tipoInventarioService;
	}
}
