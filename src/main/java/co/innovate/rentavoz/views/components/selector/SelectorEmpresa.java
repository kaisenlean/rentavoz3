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

import co.innovate.rentavoz.model.Empresa;
import co.innovate.rentavoz.services.empresa.EmpresaService;

@ManagedBean
@ViewScoped
public class SelectorEmpresa implements SelectorBase<Empresa> {

	@ManagedProperty("#{empresaService}")
	private EmpresaService empresaService;
	
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Empresa> findAll = empresaService.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione una empresa --"));
		for (Empresa empresa : findAll) {
			items.add(new SelectItem(empresa.getIdEmpresa(), empresa
					.getEmpNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 13/01/2014
	 * @param empresaService the empresaService to set
	 */
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}
}
