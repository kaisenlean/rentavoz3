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
import co.innovate.rentavoz.model.facturacion.FechaFacturacion;
import co.innovate.rentavoz.services.facturacion.FechaFacturacionService;

@ManagedBean
@ViewScoped
public class SelectorFechaFacturacion implements SelectorBase<Cuentas> {

	@ManagedProperty("#{fechaFacturacionService}")
	private FechaFacturacionService fechaFacturacionService;
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<FechaFacturacion> findAll = fechaFacturacionService.findActivas();
		items = new ArrayList<SelectItem>();
		for (FechaFacturacion fechaFacturacion : findAll) {
			items.add(new SelectItem(fechaFacturacion.getId(), fechaFacturacion
					.getLabel()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/02/2014
	 * @param fechaFacturacionService the fechaFacturacionService to set
	 */
	public void setFechaFacturacionService(
			FechaFacturacionService fechaFacturacionService) {
		this.fechaFacturacionService = fechaFacturacionService;
	}
}
