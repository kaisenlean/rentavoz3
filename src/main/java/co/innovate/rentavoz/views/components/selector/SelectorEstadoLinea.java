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

import co.innovate.rentavoz.model.EstadoLinea;
import co.innovate.rentavoz.services.estadolinea.EstadoLineaService;

@ManagedBean
@ViewScoped
public class SelectorEstadoLinea implements SelectorBase<EstadoLinea> {

	@ManagedProperty(value="#{estadoLineaService}")
	private EstadoLineaService estadoLineaService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<EstadoLinea> findAll = estadoLineaService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un estado --"));
		for (EstadoLinea estadoLinea : findAll) {
			items.add(new SelectItem(estadoLinea.getIdEstadoLinea() + "",
					estadoLinea.getEstLinNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param estadoLineaService the estadoLineaService to set
	 */
	public void setEstadoLineaService(EstadoLineaService estadoLineaService) {
		this.estadoLineaService = estadoLineaService;
	}
}
