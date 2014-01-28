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

import co.innovate.rentavoz.model.Sucursal;
import co.innovate.rentavoz.services.sucursal.SucursalService;

@ManagedBean
@ViewScoped
public class SelectorSucursal implements SelectorBase<Sucursal> {

	@ManagedProperty(value="#{sucursalService}")
	private SucursalService sucursalService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Sucursal> findAll = sucursalService.findAll();
		items = new ArrayList<SelectItem>();
		 items.add(new SelectItem(" ", "-- Seleccione una sucursal --"));
		for (Sucursal empresa : findAll) {
			items.add(new SelectItem(empresa.getIdSucursal(), empresa
					.getSucNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param sucursalService the sucursalService to set
	 */
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
}
