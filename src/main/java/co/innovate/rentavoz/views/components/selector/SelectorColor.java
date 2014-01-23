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
import co.innovate.rentavoz.model.bodega.BodegaExistenciaColor;
import co.innovate.rentavoz.services.bodegaexistencia.color.BodegaExistenciaColorService;

@ManagedBean
@ViewScoped
public class SelectorColor implements SelectorBase<Sucursal> {

	@ManagedProperty(value = "#{bodegaExistenciaColorService}")
	private BodegaExistenciaColorService bodegaExistenciaColorService;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<BodegaExistenciaColor> findAll = bodegaExistenciaColorService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(0, " - - Selecciona un Color - -"));
		for (BodegaExistenciaColor empresa : findAll) {
			items.add(new SelectItem(empresa.getId(), empresa
					.getColor()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/01/2014
	 * @param bodegaExistenciaColorService
	 *            the bodegaExistenciaColorService to set
	 */
	public void setBodegaExistenciaColorService(
			BodegaExistenciaColorService bodegaExistenciaColorService) {
		this.bodegaExistenciaColorService = bodegaExistenciaColorService;
	}
}
