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
import co.innovate.rentavoz.services.cuenta.CuentasService;

@ManagedBean
@ViewScoped
public class SelectorCuentas implements SelectorBase<Cuentas> {

	@ManagedProperty("#{cuentasService}")
	private CuentasService cuentasService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Cuentas> findAll = cuentasService.findAll();
		items = new ArrayList<SelectItem>();
//		items.add(new SelectItem(" ", "-- Seleccione una cuenta --"));
		for (Cuentas empresa : findAll) {
			items.add(new SelectItem(empresa.getIdCuentas(), empresa
					.getCueNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/01/2014
	 * @param cuentasService the cuentasService to set
	 */
	public void setCuentasService(CuentasService cuentasService) {
		this.cuentasService = cuentasService;
	}
}
