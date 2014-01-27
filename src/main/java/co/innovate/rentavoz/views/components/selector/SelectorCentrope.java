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

import co.innovate.rentavoz.model.Centrope;
import co.innovate.rentavoz.services.centrope.CentropeService;

@ManagedBean
@ViewScoped
public class SelectorCentrope implements SelectorBase<Centrope> {

	@ManagedProperty(value="#{centropeService}")
	private CentropeService centropeService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Centrope> findAll = centropeService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un Plán --"));
		for (Centrope empresa : findAll) {
			items.add(new SelectItem(empresa.getId(), empresa
					.getNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 26/01/2014
	 * @param centropeService the centropeService to set
	 */
	public void setCentropeService(CentropeService centropeService) {
		this.centropeService = centropeService;
	}
}
